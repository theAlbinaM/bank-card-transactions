package ru.almyal;

import ru.almyal.cards.BankCard;
import ru.almyal.cards.implementations.cashback.CashbackCreditCard;
import ru.almyal.cards.implementations.savings.SavingsDebitCard;

public class Main {
    public static void main(String[] args) {
        //Создание объектов всех классов и проверка корректной работы методов в тестовом классе
        BankCard debitCard = new SavingsDebitCard();
        debitCard.replenish(2000);
        System.out.println("Пополнение debitCard 2000: " + debitCard.getAvailableFundsInfo());
        debitCard.pay(1000);
        System.out.println("Оплата debitCard 1000: " + debitCard.getAvailableFundsInfo());

        BankCard creditCard = new CashbackCreditCard(10000);
        creditCard.pay(6000);
        System.out.println("Оплата creditCard 6000: " + creditCard.getAvailableFundsInfo());
        creditCard.replenish(13000);
        System.out.println("Пополнение creditCard 13000: " + creditCard.getAvailableFundsInfo());
    }
}