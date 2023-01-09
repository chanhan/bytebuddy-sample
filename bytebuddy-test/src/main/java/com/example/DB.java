package com.example;

import java.util.Random;

public class DB {
    public String query(String uid, String token) throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
        return "mysql ";
    }
}
