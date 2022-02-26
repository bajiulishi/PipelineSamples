package com.example.lib.designpattern.singleton;

/**
 * 饿汉式
 */
public class SingletonE {
    private final static SingletonE INSTANCE = new SingletonE();

    private SingletonE() {
    }

    public static SingletonE getInstance() {
        return INSTANCE;
    }
}
