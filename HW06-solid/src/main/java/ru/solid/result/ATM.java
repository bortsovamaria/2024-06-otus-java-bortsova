package ru.solid.result;

import java.util.Map;

public class ATM {
    public int getAllMoney(Cell cell) {
        return cell.getAllMoney();
    }

    public void putMoneyIntoAccount(Cell cell, Map<Integer, Integer> input) {
        cell.putMoneyToCell(input);
    }

    public void withdrawMoneyFromAccount(Cell cell, Map<Integer, Integer> input) {
        cell.withdrawMoneyFromCell(input);
    }
}