package com.example.lib.designpattern.singleton;

/**
 * 懒汉式，线程安全，性能较低
 */
public class SingletonL1 {
    private static SingletonL1 INSTANCE;

    private SingletonL1() {
    }

    public static synchronized SingletonL1 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonL1();
        }
        return INSTANCE;
    }
}
