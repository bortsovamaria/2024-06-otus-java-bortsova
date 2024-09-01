package ru.solid.result;

import java.util.HashMap;
import java.util.Map;

public class EuroCell implements Cell {
    private static int sumAllMoney = 0;
    private static final Map<Integer, Integer> banknotes;
    private static EuroCell INSTANCE;

    static {
        banknotes = new HashMap<>();
    }

    public static EuroCell getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EuroCell();
        }
        return INSTANCE;
    }

    @Override
    public int getAllMoney() {
        return sumAllMoney;
    }

    @Override
    public void putMoneyToCell(Map<Integer, Integer> input) {
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            banknotes.put(entry.getKey(), entry.getValue() + banknotes.getOrDefault(entry.getKey(), 0));
            sumAllMoney += entry.getKey() * entry.getValue();
        }
    }

    @Override
    public void withdrawMoneyFromCell(Map<Integer, Integer> input) {
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            banknotes.put(entry.getKey(), entry.getValue() + banknotes.getOrDefault(entry.getKey(), 0));
            sumAllMoney -= entry.getKey() * entry.getValue();
        }
    }
}