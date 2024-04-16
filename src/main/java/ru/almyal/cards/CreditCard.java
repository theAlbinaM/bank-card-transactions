package ru.almyal.cards;

import java.util.Map;

public class CreditCard extends BankCard {
    private final double creditLimit;
    private double creditFunds;
    private double ownFunds;

    public CreditCard(double creditLimit) {
        this.creditLimit = creditLimit;
        this.creditFunds = creditLimit;
        super.balance = creditLimit;
        this.ownFunds = 0;
    }

    @Override
    public void replenish(double amount) {
        balance += amount;
        if (balance > creditLimit) {
            creditFunds = creditLimit;
            ownFunds = balance - creditFunds;
        } else {
            ownFunds = 0;
            creditFunds = balance;
        }
    }

    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            if (balance <= creditLimit) {
                creditFunds = balance;
                ownFunds = 0;
            } else {
                creditFunds = creditLimit;
                ownFunds = balance - creditFunds;
            }
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Double> getAvailableFundsInfo() {
        return Map.of("Balance", balance,
                "Credit Limit", creditLimit,
                "Credit Funds", creditFunds,
                "Own Funds", ownFunds);
    }
}
