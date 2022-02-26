package com.example.lib.designpattern.adapter;

public class Main {
    public static void main(String[] args) {
        Duck duck = new TurkeyAdapter(new TurkeyImpl());
        duck.quack();
        duck.fly();
    }
}
