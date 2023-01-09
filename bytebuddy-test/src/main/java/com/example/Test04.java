package com.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.UUID;

public class Test04 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        DynamicType.Unloaded<DB> dynamicType = new ByteBuddy()
                .subclass(DB.class)
                .method(ElementMatchers.named("query"))
                .intercept(MethodDelegation.to(MonitorDemo.class))
                .make();
        ClassFile.outputClazz(dynamicType.getBytes(),"ByteBuddyDB");
        Class<?> clazz = dynamicType.load(Test04.class.getClassLoader()).getLoaded();
        ((DB)clazz.newInstance()).query(UUID.randomUUID().toString(),"token");
    }
}
