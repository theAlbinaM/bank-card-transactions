package ru.almyal;

import org.junit.Test;
import ru.almyal.cards.BankCard;
import ru.almyal.cards.CreditCard;
import ru.almyal.cards.DebitCard;
import ru.almyal.cards.implementations.bonus.BonusCreditCard;
import ru.almyal.cards.implementations.bonus.BonusDebitCard;
import ru.almyal.cards.implementations.cashback.CashbackCreditCard;
import ru.almyal.cards.implementations.cashback.CashbackDebitCard;
import ru.almyal.cards.implementations.savings.SavingsCreditCard;
import ru.almyal.cards.implementations.savings.SavingsDebitCard;

import static org.junit.Assert.assertEquals;

public class BankCardTest {

    @Test
    public void DebitCardOperationTest() {
        BankCard card = new DebitCard();
        card.replenish(10000);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        card.pay(8000);
        assertEquals(2000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        card.pay(8000);
        assertEquals(2000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
    }

    @Test
    public void CreditCardOperationTest() {
        BankCard card = new CreditCard(10000);
        card.replenish(2000);
        assertEquals(12000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(2000.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        card.pay(8000);
        assertEquals(4000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(4000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        card.pay(10000);
        assertEquals(4000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(4000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
    }

    @Test
    public void BonusDebitCardOperationTest() {
        BankCard card = new BonusDebitCard();
        card.replenish(10000);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);
        card.pay(8000);
        assertEquals(2080.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(80.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);
        card.replenish(3000);
        assertEquals(5080.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(80.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);
        card.pay(8);
        assertEquals(5072.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(72.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);
    }

    @Test
    public void BonusCreditCardOperationTest() {
        BankCard card = new BonusCreditCard(5000);
        card.pay(4000);
        assertEquals(1040.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(1000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(40.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);

        card.replenish(10000);
        assertEquals(11040.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(5000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(6040.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);

        card.replenish(960);
        assertEquals(12000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(5000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(7000.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Bonuses"), 0.00);
    }

    @Test
    public void CashbackDebitCardOperationTest() {
        BankCard card = new CashbackDebitCard();
        card.replenish(6000);
        assertEquals(6000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);
        card.pay(5000);
        assertEquals(1250.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(250.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);
        card.pay(250);
        assertEquals(1000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);
    }

    @Test
    public void CashbackCreditCardOperationTest() {
        BankCard card = new CashbackCreditCard(10000);
        card.pay(5000);
        assertEquals(5250.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(5000, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(250.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);

        card.pay(5000);
        assertEquals(500.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(250.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(250.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);

        card.replenish(15000);
        assertEquals(15500.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(5500.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Cashback"), 0.00);
    }

    @Test
    public void SavingsDebitCardOperationTest() {
        BankCard card = new SavingsDebitCard();
        card.replenish(6000);
        assertEquals(6030.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(30.00, card.getAvailableFundsInfo().get("Savings"), 0.00);
        card.pay(5000);
        assertEquals(1030.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Savings"), 0.00);
        card.pay(1250);
        assertEquals(1030.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Savings"), 0.00);
    }

    @Test
    public void SavingsCreditCardOperationTest() {
        BankCard card = new SavingsCreditCard(10000);
        card.pay(5000);
        assertEquals(5000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(5000, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Savings"), 0.00);

        card.replenish(15000);
        assertEquals(20075.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(75.00, card.getAvailableFundsInfo().get("Savings"), 0.00);

        card.pay(10075);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Balance"), 0.00);
        assertEquals(10000.00, card.getAvailableFundsInfo().get("Credit Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Own Funds"), 0.00);
        assertEquals(0.00, card.getAvailableFundsInfo().get("Savings"), 0.00);
    }
}