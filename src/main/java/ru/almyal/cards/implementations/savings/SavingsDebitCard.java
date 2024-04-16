package ru.almyal.cards.implementations.savings;

import ru.almyal.cards.DebitCard;

import java.util.Map;

public class SavingsDebitCard extends DebitCard {
    private double savings = 0;
    private static final double RATE = 0.005;

    @Override
    public void replenish(double amount) {
        super.replenish(amount);
        balance += amount * RATE;
        savings += amount * RATE;
    }

    @Override
    public boolean pay(double amount) {
        if (savings < amount && balance >= amount) {
            balance -= savings;
            amount -= savings;
            savings = 0;
        }
        if (savings >= amount) {
            savings -= amount;
            balance -= amount;
            return true;
        }
        return super.pay(amount);
    }

    @Override
    public Map<String, Double> getAvailableFundsInfo() {
        return Map.of("Balance", super.getAvailableFundsInfo().get("Balance"),
                "Savings", savings);
    }
}
