package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float amount;

    private float incentive; // new field ✅

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserRecord sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserRecord recipient;

    public TransactionRecord() {}

    public TransactionRecord(UserRecord sender, UserRecord recipient, float amount, float incentive) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.incentive = incentive;
    }

    // Getters and setters
    public Long getId() { return id; }
    public float getAmount() { return amount; }
    public float getIncentive() { return incentive; }
    public UserRecord getSender() { return sender; }
    public UserRecord getRecipient() { return recipient; }

    public void setId(Long id) { this.id = id; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setIncentive(float incentive) { this.incentive = incentive; }
    public void setSender(UserRecord sender) { this.sender = sender; }
    public void setRecipient(UserRecord recipient) { this.recipient = recipient; }
}
