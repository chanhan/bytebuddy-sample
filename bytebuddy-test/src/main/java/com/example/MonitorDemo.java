package com.example;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MonitorDemo {
    @RuntimeType
    public static Object intercept(@Origin Class<?> clazz,
                                   @AllArguments Object[] allArguments,
                                   @Origin Method method,
                                   @SuperCall Callable<?> zuper) throws Throwable {
        System.out.println("开始执行");
        Object ret = null;
        try {
            System.out.println("    class name = " + clazz.getName());
            System.out.println("    method name = " + method.getName());
            ret = zuper.call();
        } catch (Throwable t) {
            throw t;
        } finally {
            System.out.println("完成执行");
        }
        return ret;
    }
}
