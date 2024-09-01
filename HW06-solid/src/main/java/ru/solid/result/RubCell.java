package ru.solid.result;

import java.util.HashMap;
import java.util.Map;

public class RubCell implements Cell {
    private static int sum = 0;
    private static final Map<Integer, Integer> BANKNOTES;
    private static RubCell INSTANCE;

    private RubCell() {

    }

    static {
        BANKNOTES = new HashMap<>();
    }

    public static RubCell getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RubCell();
        }
        return INSTANCE;
    }

    @Override
    public int getAllMoney() {
        return sum;
    }

    public void putMoneyToCell(Map<Integer, Integer> input) {
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            BANKNOTES.put(entry.getKey(), entry.getValue() + BANKNOTES.getOrDefault(entry.getKey(), 0));
            sum += entry.getKey() * entry.getValue();
        }
    }

    @Override
    public void withdrawMoneyFromCell(Map<Integer, Integer> input) {
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            BANKNOTES.put(entry.getKey(), entry.getValue() + BANKNOTES.getOrDefault(entry.getKey(), 0));
            sum -= entry.getKey() * entry.getValue();
        }
    }
}