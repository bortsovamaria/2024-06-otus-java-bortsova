package ru.solid.result;

public class Bank {
    public Cell getCell(NominalValue nominalValue) {
        switch (nominalValue) {
            case RUB -> {
                return RubCell.getInstance();
            }
            case EURO -> {
                return EuroCell.getInstance();
            }
        }
        throw new IllegalArgumentException("Not found nominal: " + nominalValue.name());
    }
}