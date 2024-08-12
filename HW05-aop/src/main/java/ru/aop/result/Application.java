package ru.aop.result;

public class Application {
    public static void main(String[] args) {
        TestLoggingInterface myClass = IoC.createMyClass();
        myClass.calculation(6);

        myClass.calculation(6, 5);

        myClass.calculation(6, 7, 8);

        myClass.calculation("test");
    }
}