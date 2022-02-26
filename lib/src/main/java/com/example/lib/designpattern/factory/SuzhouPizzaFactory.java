package com.example.lib.designpattern.factory;

public class SuzhouPizzaFactory implements StoreFactory {
    @Override
    public Pizza createPizza() {
        return new Pizza("苏州偏甜的口味儿");
    }
}
