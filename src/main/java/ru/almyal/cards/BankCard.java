package ru.almyal.cards;

import java.util.Map;

public abstract class BankCard {
    protected double balance;
    public abstract void replenish(double amount);

    public abstract boolean pay(double amount);

    public Map<String, Double> getBalanceInfo(){
        return Map.of("Balance", balance);
    }

    public Map<String, Double> getAvailableFundsInfo(){
        return getBalanceInfo();
    }
}
