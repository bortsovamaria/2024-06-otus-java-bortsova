package ru.solid.result;

import java.util.Map;

public interface Cell {

    void putMoneyToCell(Map<Integer, Integer> input);

    void withdrawMoneyFromCell(Map<Integer, Integer> input);

    int getAllMoney();
}