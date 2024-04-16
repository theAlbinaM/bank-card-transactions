package ru.almyal.cards.implementations.cashback;

import ru.almyal.cards.CreditCard;

import java.util.Map;

public class CashbackCreditCard extends CreditCard {
    private double cashback = 0;
    private static final double RATE = 0.05;

    public CashbackCreditCard(double creditLimit) {
        super(creditLimit);
    }

    @Override
    public void replenish(double amount) {
        super.replenish(amount);
        cashback = 0;
    }

    @Override
    public boolean pay(double amount) {
        double amountWithoutCashback = amount;
        if (cashback < amount && balance >= amount) {
            balance = balance - cashback;
            amountWithoutCashback = amount - cashback;
            cashback = 0;
        }
        if (cashback >= amount) {
            cashback -= amount;
            balance -= amount;
            return true;
        }
        if (super.pay(amountWithoutCashback)) {
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
                "Credit Limit", super.getAvailableFundsInfo().get("Credit Limit"),
                "Credit Funds", super.getAvailableFundsInfo().get("Credit Funds"),
                "Own Funds", super.getAvailableFundsInfo().get("Own Funds"),
                "Cashback", cashback);
    }
}
