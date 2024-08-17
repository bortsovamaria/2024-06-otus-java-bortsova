package ru.aop.result;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.nonNull;

@Slf4j
class IoC {

    private IoC() {
    }

    static TestLoggingInterface createMyClass(TestLoggingInterface testLoggingClass) {
        CustomInvocationHandler handler = new CustomInvocationHandler(testLoggingClass);

        return (TestLoggingInterface) Proxy.newProxyInstance(
                IoC.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class CustomInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private final Set<MethodDTO> cacheMethods;

        CustomInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            this.cacheMethods = getMethodsByAnnotation(myClass.getClass(), Log.class);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            MethodDTO currentMethod = getCurrentMethodDTO(method);

            if (cacheMethods.contains(currentMethod)) {
                log.info("executed method from proxy: {}, param: {}", method, args);
                return method.invoke(myClass, args);
            }
            return method.invoke(myClass, args);
        }

        private Set<MethodDTO> getMethodsByAnnotation(Class<? extends TestLoggingInterface> clazz,
                                                      Class<? extends Annotation> annotation) {
            Method[] methods = clazz.getMethods();
            Set<MethodDTO> methodsByAnnotation = new HashSet<>();
            for (Method method : methods) {
                if (nonNull(method.getAnnotation(annotation))) {
                    methodsByAnnotation.add(getCurrentMethodDTO(method));
                }
            }
            return methodsByAnnotation;
        }

        private MethodDTO getCurrentMethodDTO(Method method) {
            return MethodDTO.builder()
                    .name(method.getName())
                    .parametersName(method.getParameterTypes())
                    .build();
        }

        @Override
        public String toString() {
            return "CustomInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}