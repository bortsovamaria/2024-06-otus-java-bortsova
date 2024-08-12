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

    private IoC() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new CustomInvocationHandler(new TestLoggingImpl());
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

    private static List<Method> getMethodsByAnnotation(Class<? extends Annotation> annotation)
            throws ClassNotFoundException {
        Class<?> clazz = Class.forName("ru.aop.result.TestLoggingImpl");
        Method[] methods = clazz.getMethods();
        List<Method> result = new ArrayList<>();
        for (Method method : methods) {
            if (nonNull(method.getAnnotation(annotation))) {
                result.add(method);
            }
        }
        return result;
    }

    private static boolean isExistMethod(Method method) throws ClassNotFoundException {
        return getMethodsByAnnotation(Log.class)
                .stream()
                .filter(m -> m.getName().equals(method.getName()))
                .filter(m -> m.getParameterCount() == method.getParameterCount())
                .anyMatch(m -> Arrays.equals(method.getParameterTypes(), m.getParameterTypes()));
    }
}