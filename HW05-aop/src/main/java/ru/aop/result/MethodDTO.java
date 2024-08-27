package ru.aop.result;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Builder
public class MethodDTO {

    private String name;

    private Class<?>[] parameters;
}
