package ru.calculator;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Data {
    private int value;

    public Data(int value) {
        this.value = value;
    }
}