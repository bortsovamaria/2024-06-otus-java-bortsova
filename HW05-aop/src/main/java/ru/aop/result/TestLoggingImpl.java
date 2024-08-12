package ru.aop.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLoggingImpl implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLoggingImpl.class);

    @Log
    @Override
    public void calculation(int param1) {
        logger.info("execute method calculation(int) from original class");
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        logger.info("execute method calculation(int, int) from original class");
    }

    @Log
    @Override
    public void calculation(int param1, int param2, int param3) {
        logger.info("execute method calculation(int, int, int) from original class");

    }

    @Override
    public void calculation(String param) {
        logger.info("execute method calculation(String) from original class");

    }

    @Override
    public String toString() {
        return "TestLoggingImpl{}";
    }
}