package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClassFile {

    public static void outputClazz(byte[] bytes, String className) {
        FileOutputStream out = null;
        try {
            String pathName = Test01.class.getResource("/").getPath() + className + ".class";
            out = new FileOutputStream(new File(pathName));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
