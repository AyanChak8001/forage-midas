package com.jpmc.midascore.foundation;
// src/main/java/com/jpmc/midascore/foundation/Incentive.java

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Incentive {
    private Double amount;

    public Incentive() {}

    public Incentive(Double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Incentive{amount=" + amount + '}';
    }
}
