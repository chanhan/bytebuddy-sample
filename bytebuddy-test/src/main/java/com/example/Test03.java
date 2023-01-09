package com.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class Test03 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.example.HelloWorld")
                .defineMethod("main",void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class,"args")
                .intercept(MethodDelegation.to(Hello.class))
                .make();
        ClassFile.outputClazz(dynamicType.getBytes(),"HelloWorld");
        Class<?> clazz = dynamicType.load(Test03.class.getClassLoader()).getLoaded();
        clazz.getMethod("main",String[].class).invoke(clazz.newInstance(), (Object) new String[]{"test"});
    }
}
