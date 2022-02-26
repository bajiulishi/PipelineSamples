package com.example.lib.designpattern.singleton;

/**
 * 静态内部类
 */
public class SingletonHolder {

    private SingletonHolder() {
    }

    private static class Holder {
        private static volatile SingletonHolder INSTANCE = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        return Holder.INSTANCE;
    }
}
