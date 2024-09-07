package ru.solid.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Banknote {
    FIVE(5), TEN(10), FIFTY(50), HUNDRED(100);

    private final int value;
}
