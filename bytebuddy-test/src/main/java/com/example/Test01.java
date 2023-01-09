package com.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Test01 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make();

        ClassFile.outputClazz(dynamicType.getBytes(), "Test");
        String helloWorld = dynamicType.load(Test01.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);
    }

}
