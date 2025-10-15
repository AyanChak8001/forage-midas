//package com.jpmc.midascore.Kafka;

//import com.jpmc.midascore.foundation.Transaction;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class TransactionListener {
//
//    /* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
//    /**
//     * Listen for incoming transactions from Kafka and process them.
//     *
//     * @param record a ConsumerRecord containing the transaction key and value
//     */
//    @KafkaListener(
//            topics = "${general.kafka-topic}"
//    )
//    public void listen(ConsumerRecord<String, Transaction> record) {
//        Transaction t = record.value();
//        if (t != null) {
//            log.info("Received transaction in listener: {}", t);
//            // Put a breakpoint here for debugging t.getAmount() during TaskTwoTests
//        } else {
//            log.warn("Received null transaction from Kafka topic: {}", record.topic());
//        }
//        // no further processing required for Task Two
//    }
//}
