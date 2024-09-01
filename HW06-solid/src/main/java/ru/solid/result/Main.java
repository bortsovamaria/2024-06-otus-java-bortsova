package ru.solid.result;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Bank bank = new Bank();
        Cell rubCell = bank.getCell(NominalValue.RUB);

        int geld = atm.getAllMoney(rubCell);
        log.info("Get geld: {}", geld);

        Map<Integer, Integer> rubBanknotes = new HashMap<>();
        rubBanknotes.put(500, 2);
        rubBanknotes.put(1000, 1);
        atm.putMoneyIntoAccount(rubCell, rubBanknotes);
        log.info("After put: {}", atm.getAllMoney(rubCell));

        Map<Integer, Integer> rubBanknotesSecond = new HashMap<>();
        rubBanknotesSecond.put(500, 1);
        atm.withdrawMoneyFromAccount(rubCell, rubBanknotesSecond);
        log.info("After withdraw: {}", atm.getAllMoney(rubCell));

    }
}