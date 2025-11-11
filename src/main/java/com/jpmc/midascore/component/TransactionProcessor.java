package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRecordRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionProcessor {
    private static final Logger logger = LoggerFactory.getLogger(TransactionProcessor.class);
    
    private final UserRepository userRepository;
    private final TransactionRecordRepository transactionRecordRepository;
    private final IncentiveClient incentiveClient;

    public TransactionProcessor(UserRepository userRepository, TransactionRecordRepository transactionRecordRepository, IncentiveClient incentiveClient) {
        this.userRepository = userRepository;
        this.transactionRecordRepository = transactionRecordRepository;
        this.incentiveClient = incentiveClient;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core-group")
    @Transactional
    public void processTransaction(Transaction transaction) {
        logger.info("Received transaction: {}", transaction);
        
        // Find sender and recipient
        UserRecord sender = userRepository.findById(transaction.getSenderId()).orElse(null);
        UserRecord recipient = userRepository.findById(transaction.getRecipientId()).orElse(null);
        
        // Validate transaction
        if (sender == null) {
            logger.warn("Transaction rejected: sender {} not found", transaction.getSenderId());
            return;
        }
        
        if (recipient == null) {
            logger.warn("Transaction rejected: recipient {} not found", transaction.getRecipientId());
            return;
        }
        
        if (sender.getBalance() < transaction.getAmount()) {
            logger.warn("Transaction rejected: sender {} has insufficient balance (has: {}, needs: {})", 
                sender.getName(), sender.getBalance(), transaction.getAmount());
            return;
        }
        
        // Transaction is valid - get incentive
        float incentive = incentiveClient.getIncentive(transaction);
        logger.info("Incentive amount: {}", incentive);
        
        // Transaction is valid - process it
        logger.info("Processing valid transaction: {} from {} (balance: {}) to {} (balance: {})",
            transaction.getAmount(), sender.getName(), sender.getBalance(), 
            recipient.getName(), recipient.getBalance());
        
        // Update balances
        // Deduct only the transaction amount from sender
        sender.setBalance(sender.getBalance() - transaction.getAmount());
        // Add transaction amount AND incentive to recipient
        recipient.setBalance(recipient.getBalance() + transaction.getAmount() + incentive);
        
        // Save updated users
        userRepository.save(sender);
        userRepository.save(recipient);
        
        // Record transaction with incentive
        TransactionRecord transactionRecord = new TransactionRecord(sender, recipient, transaction.getAmount(), incentive);
        transactionRecordRepository.save(transactionRecord);
        
        logger.info("Transaction completed: {} from {} (new balance: {}) to {} (new balance: {}, received incentive: {})",
            transaction.getAmount(), sender.getName(), sender.getBalance(), 
            recipient.getName(), recipient.getBalance(), incentive);
    }
}

