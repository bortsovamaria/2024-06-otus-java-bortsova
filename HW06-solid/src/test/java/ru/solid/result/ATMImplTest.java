package ru.solid.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ATMImplTest {
    private final ATM atm = new ATMImpl();

    @Test
    void shouldPutMoney() {
        atm.putMoneyIntoAccount(Banknote.FIFTY, 3);
        atm.putMoneyIntoAccount(Banknote.HUNDRED, 3);
        atm.putMoneyIntoAccount(Banknote.TEN, 3);
        assertEquals(480, atm.getAllMoney());
    }

    @Test
    void shouldWithdrawMoney() {
        atm.putMoneyIntoAccount(Banknote.FIFTY, 4);
        int beforeWithdraw = atm.getAllMoney();

        atm.withdrawMoneyFromAccount(150);
        int afterWithdraw = atm.getAllMoney();

        assertEquals(beforeWithdraw - 150, afterWithdraw);
    }

    @Test
    void shouldWithdrawMoneyWithException() {
        atm.putMoneyIntoAccount(Banknote.FIFTY, 1);

        assertThrows(WithdrawException.class, () -> atm.withdrawMoneyFromAccount(100));
    }
}