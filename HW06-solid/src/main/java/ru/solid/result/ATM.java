package ru.solid.result;

public interface ATM {
    void putMoneyIntoAccount(Banknote banknote, int count);

    void withdrawMoneyFromAccount(int sum);

    int getAllMoney();
}