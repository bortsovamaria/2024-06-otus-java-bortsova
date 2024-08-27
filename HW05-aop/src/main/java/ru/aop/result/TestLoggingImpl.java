package ru.aop.result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLoggingImpl implements TestLoggingInterface {

    @Log
    @Override
    public void calculation(int param1) {
        log.info("execute method calculation(int) from original class");
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        log.info("execute method calculation(int, int) from original class");
    }

    @Log
    @Override
    public void calculation(int param1, int param2, int param3) {
        log.info("execute method calculation(int, int, int) from original class");

    }

    @Override
    public void calculation(String param) {
        log.info("execute method calculation(String) from original class");

    }

    @Override
    public String toString() {
        return "TestLoggingImpl{}";
    }
}