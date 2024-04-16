package ru.almyal.cards;

public class DebitCard extends BankCard {
    @Override
    public void replenish(double amount) {
        balance += amount;
    }

    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        }
        return false;
    }
}
