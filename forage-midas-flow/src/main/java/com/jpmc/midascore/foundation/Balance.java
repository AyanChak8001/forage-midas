package com.jpmc.midascore.foundation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {

    private long userId;
    private double balance;

    // Default constructor (needed by Jackson)
    public Balance() {
    }

    // Constructor for setting userId and balance
    public Balance(long userId, double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Keep toString as required (won't affect JSON serialization)
    @Override
    public String toString() {
        return "Balance {balance=" + balance + "}";
    }
}
