package com.jpmc.midascore;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    private final String topic;
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public KafkaProducer(@Value("${general.kafka-topic}") String topic, KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String transactionLine) {
        String[] transactionData = transactionLine.split(",\\s*"); // handle optional spaces
        try {
            long field1 = Long.parseLong(transactionData[0].trim());
            long field2 = Long.parseLong(transactionData[1].trim());

            // Clean float string to remove any unexpected characters like newlines
            String floatStr = transactionData[2].replaceAll("[^0-9.]", "");
            float field3 = Float.parseFloat(floatStr);

            kafkaTemplate.send(topic, new Transaction(field1, field2, field3));
        } catch (NumberFormatException e) {
            System.err.println("Invalid transaction line: " + transactionLine);
            e.printStackTrace();
        }
    }
}