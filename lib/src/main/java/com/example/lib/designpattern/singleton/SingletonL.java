package com.example.lib.designpattern.singleton;

/**
 * 懒汉式，线程不安全
 */
public class SingletonL {
    private static SingletonL INSTANCE;

    private SingletonL() {
    }

    public static SingletonL getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonL();
        }
        return INSTANCE;
    }
}
