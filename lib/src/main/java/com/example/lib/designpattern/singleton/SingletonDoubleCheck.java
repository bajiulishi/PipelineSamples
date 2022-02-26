package com.example.lib.designpattern.singleton;

/**
 * 懒汉式，线程安全，性能较高
 */
public class SingletonDoubleCheck {
    private static volatile SingletonDoubleCheck INSTANCE;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDoubleCheck();
                }
            }
        }
        return INSTANCE;
    }
}
