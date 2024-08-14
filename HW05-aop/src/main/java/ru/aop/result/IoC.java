package ru.aop.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.nonNull;

class IoC {
    private static final Logger logger = LoggerFactory.getLogger(IoC.class);

    private static final List<List<String>> methods = getMethodsByAnnotation(Log.class);

    private IoC() {
    }

    static TestLoggingInterface createMyClass(TestLoggingInterface testLoggingClass) {
        InvocationHandler handler = new CustomInvocationHandler(testLoggingClass);
        return (TestLoggingInterface) Proxy.newProxyInstance(
                IoC.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class CustomInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;

        CustomInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isExistMethod(method)) {
                logger.info("executed method from proxy: {}, param: {}", method, args);
                return method.invoke(myClass, args);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "CustomInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }

    private static boolean isExistMethod(Method method) {
        return methods.contains(getListParameterNamesByMethod(method));
    }

    private static List<List<String>> getMethodsByAnnotation(Class<? extends Annotation> annotation) {
        Class<?> clazz;
        try {
            clazz = Class.forName(TestLoggingImpl.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found");
        }
        Method[] methods = clazz.getMethods();
        List<List<String>> result = new ArrayList<>();
        for (Method method : methods) {
            if (nonNull(method.getAnnotation(annotation))) {
                List<String> list = getListParameterNamesByMethod(method);
                result.add(list);
            }
        }
        return result;
    }

    private static List<String> getListParameterNamesByMethod(Method method) {
        return Arrays.stream(method.getParameters())
                .map(m -> m.getParameterizedType().getTypeName())
                .toList();
    }
}