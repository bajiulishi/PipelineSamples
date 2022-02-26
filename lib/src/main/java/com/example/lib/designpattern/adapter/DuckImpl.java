package com.example.lib.designpattern.adapter;

public class DuckImpl implements Duck {
    @Override
    public void quack() {
        System.out.println("鸭子呱呱叫");
    }

    @Override
    public void fly() {
        System.out.println("鸭子飞起来了");
    }
}
