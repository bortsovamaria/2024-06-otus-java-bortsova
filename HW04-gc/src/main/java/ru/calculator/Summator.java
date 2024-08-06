package ru.calculator;

import lombok.Getter;

@Getter
public class Summator {
    private int sum = 0;
    private int prevValue = 0;
    private int prevPrevValue = 0;
    private int sumLastThreeValues = 0;
    private int someValue = 0;
    private final Data[] listValues = new Data[100_000];
    private int index = 0;

    // !!! сигнатуру метода менять нельзя
    public void calc(Data data) {
        listValues[index] = data;
        if (index % 100_000 == 0) {
            index = 0;
        }
        sum += data.getValue();

        sumLastThreeValues = data.getValue() + prevValue + prevPrevValue;

        prevPrevValue = prevValue;
        prevValue = data.getValue();

        for (var idx = 0; idx < 3; idx++) {
            someValue += (sumLastThreeValues * sumLastThreeValues / (data.getValue() + 1) - sum);
            someValue = Math.abs(someValue) + index;
        }
    }
}
