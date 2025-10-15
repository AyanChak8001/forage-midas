package com.jpmc.midascore.Model;

public class Transaction {
    private long senderId;
    private long recipientId;
    private float amount;

    private Double incentive;

    public Transaction() {}

    public Transaction(long senderId, long recipientId, float amount, Double incentive) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.amount = amount;
        this.incentive = incentive;
    }

    public long getSenderId() { return senderId; }
    public void setSenderId(long senderId) { this.senderId = senderId; }

    public long getRecipientId() { return recipientId; }
    public void setRecipientId(long recipientId) { this.recipientId = recipientId; }

    public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }

    public Double getIncentive() { return incentive == null ? 0.0 : incentive; }
    public void setIncentive(Double incentive) { this.incentive = incentive; }

    @Override
    public String toString() {
        return "Transaction {senderId=" + senderId + ", recipientId=" + recipientId + ", amount=" + amount + "}";
    }
}
