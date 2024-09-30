package ru.otus.jdbc.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * "Разбирает" объект на составные части
 */
public interface EntityClassMetaData<T> {
    String getName();

    Constructor<T> getConstructor();

    //Поле Id определятся по наличию аннотации Id
    Field getIdField();

    List<Field> getAllFields();

    List<Field> getFieldsWithoutId();
}
