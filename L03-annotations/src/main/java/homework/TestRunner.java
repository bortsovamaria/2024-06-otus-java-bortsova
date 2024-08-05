package homework;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    public static void main(String[] args) {
        runTests("homework.AnnotationTest");
    }

    @SneakyThrows
    private static void runTests(String className) {
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getMethods();
        List<Method> beforeMethods = getMethodsByAnnotation(methods, Before.class);
        List<Method> testMethods = getMethodsByAnnotation(methods, Test.class);
        List<Method> afterMethods = getMethodsByAnnotation(methods, After.class);
        int successTests = 0;
        int failedTests = 0;

        for (Method testMethod : testMethods) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            try {
                for (Method beforeMethod : beforeMethods) {
                    beforeMethod.invoke(instance);
                }
                testMethod.invoke(instance);
                successTests++;
            } catch (Exception e) {
                failedTests++;
            } finally {
                for (Method afterMethod : afterMethods) {
                    afterMethod.invoke(instance);
                }
            }
        }

        logger.info("Success tests: {}", successTests);
        logger.info("Failed tests: {}", failedTests);
    }

    private static List<Method> getMethodsByAnnotation(Method[] methods, Class<? extends Annotation> annotation) {
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (method.getAnnotation(annotation) != null) {
                result.add(method);
            }
        }
        return result;
    }
}
