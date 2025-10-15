package com.jpmc.midascore.Service;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.Model.Incentive;
import com.jpmc.midascore.Model.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public TransactionService(UserRepository userRepository,
                              TransactionRepository transactionRepository,
                              RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    public void processTransaction(Transaction transaction) {
        Optional<UserRecord> senderOpt = userRepository.findById(transaction.getSenderId());
        Optional<UserRecord> recipientOpt = userRepository.findById(transaction.getRecipientId());

        if (senderOpt.isEmpty() || recipientOpt.isEmpty()) return;

        UserRecord sender = senderOpt.get();
        UserRecord recipient = recipientOpt.get();

        float amount = transaction.getAmount();

        // Validation
        if (sender.getBalance() < amount) return;

        // ✅ Call Incentive API
        String url = "http://localhost:8080/incentive";
        Incentive incentiveResponse = restTemplate.postForObject(url, transaction, Incentive.class);

        float incentiveAmount = incentiveResponse != null ? incentiveResponse.getAmount() : 0;

        // Adjust balances
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount + incentiveAmount);

        // Save updated balances
        userRepository.save(sender);
        userRepository.save(recipient);

        // Record transaction
        TransactionRecord record = new TransactionRecord(sender, recipient, amount, incentiveAmount);
        transactionRepository.save(record);
    }
}
