package com.example.lib.designpattern.adapter;

public class TurkeyImpl implements Turkey {

    @Override
    public void gobble() {
        System.out.println("火鸡咯咯叫");
    }

    @Override
    public void fly() {
        System.out.println("火鸡飞起来了");
    }
}
