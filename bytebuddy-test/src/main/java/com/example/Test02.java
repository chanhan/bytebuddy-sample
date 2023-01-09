package com.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Modifier;

public class Test02 {
    public static void main(String[] args) {
        DynamicType.Unloaded<Object> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .name("com.example.HelloWorld")
                .defineMethod("main",void.class, Modifier.PUBLIC + Modifier.STATIC)
                .withParameter(String[].class,"args")
                .intercept(FixedValue.value("hello world"))
                .make();

        ClassFile.outputClazz(dynamicType.getBytes(),"HelloWorld");

    }
}
