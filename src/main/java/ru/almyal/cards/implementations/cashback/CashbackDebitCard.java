package ru.almyal.cards.implementations.cashback;

import ru.almyal.cards.DebitCard;

import java.util.Map;

public class CashbackDebitCard extends DebitCard {
    private double cashback = 0;
    private static final double RATE = 0.05;

    @Override
    public boolean pay(double amount) {
        if (cashback < amount && balance >= amount) {
            balance -= cashback;
            amount = amount - cashback;
            cashback = 0;
        }
        if (cashback >= amount) {
            cashback -= amount;
            balance -= amount;
            return true;
        }
        if (super.pay(amount)) {
            if (amount >= 5000) {
                cashback += amount * RATE;
                balance += amount * RATE;
            }
            return true;
        }
        return false;
    }
    @Override
    public Map<String, Double> getAvailableFundsInfo() {
        return Map.of("Balance", super.getAvailableFundsInfo().get("Balance"),
                "Cashback", cashback);
    }
}
