package ru.solid.result;

import java.util.HashMap;
import java.util.Map;

public class ATMImpl implements ATM {
    private final Map<Banknote, Integer> store = new HashMap<>();

    @Override
    public void putMoneyIntoAccount(Banknote banknote, int count) {
        int banknotesCount = store.getOrDefault(banknote, 0);
        store.put(banknote, banknotesCount + count);
    }

    @Override
    public void withdrawMoneyFromAccount(int sum) {
        if (getAllMoney() < sum) {
            throw new WithdrawException("Not available money");
        }
        Map<Banknote, Integer> withdraw = new HashMap<>();
        int remainder = sum;
        for (Banknote banknote : Banknote.values()) {
            int notesCount = store.getOrDefault(banknote, 0);
            if (notesCount > 0) {
                int neededNotes = remainder / banknote.getValue();
                if (neededNotes > 0) {
                    int notesToGet = Math.min(notesCount, neededNotes);
                    withdraw.put(banknote, notesToGet);
                    remainder -= banknote.getValue() * notesToGet;
                }
            }
        }

        for (Map.Entry<Banknote, Integer> entry : withdraw.entrySet()) {
            Banknote banknote = entry.getKey();
            Integer count = entry.getValue();
            store.put(banknote, store.get(banknote) - count);
        }
    }

    public int getAllMoney() {
        return store.entrySet().stream()
                .mapToInt(e -> e.getKey().getValue() * e.getValue())
                .sum();
    }
}