package ru.almyal.cards.implementations.bonus;

import ru.almyal.cards.DebitCard;

import java.util.Map;

public class BonusDebitCard extends DebitCard {
    private double bonuses = 0;
    private static final double RATE = 0.01;

    @Override
    public boolean pay(double amount) {
        if (bonuses < amount && balance >= amount) {
            balance = balance - bonuses;
            amount = amount - bonuses;
            bonuses = 0;
        }
        if (bonuses >= amount) {
            bonuses = bonuses - amount;
            balance = balance - amount;
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
                "Bonuses", bonuses);
    }
}
