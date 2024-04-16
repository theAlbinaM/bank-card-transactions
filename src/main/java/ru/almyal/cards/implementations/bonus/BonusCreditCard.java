package ru.almyal.cards.implementations.bonus;

import ru.almyal.cards.CreditCard;

import java.util.Map;

public class BonusCreditCard extends CreditCard {
    private double bonuses = 0;
    private static final double RATE = 0.01;

    public BonusCreditCard(double creditLimit) {
        super(creditLimit);
    }

    @Override
    public void replenish(double amount) {
        super.replenish(amount);
        bonuses = 0;
    }

    @Override
    public boolean pay(double amount) {
        if (bonuses < amount && balance >= amount) {
            balance -= bonuses;
            amount -= bonuses;
            bonuses = 0;
        }
        if (bonuses >= amount) {
            bonuses -= amount;
            balance -= amount;
            return true;
        }
        if (super.pay(amount)) {
            bonuses += amount * RATE;
            balance += amount * RATE;
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Double> getAvailableFundsInfo() {
        return Map.of("Balance", super.getAvailableFundsInfo().get("Balance"),
                "Credit Limit", super.getAvailableFundsInfo().get("Credit Limit"),
                "Credit Funds", super.getAvailableFundsInfo().get("Credit Funds"),
                "Own Funds", super.getAvailableFundsInfo().get("Own Funds"),
                "Bonuses", bonuses);
    }
}
