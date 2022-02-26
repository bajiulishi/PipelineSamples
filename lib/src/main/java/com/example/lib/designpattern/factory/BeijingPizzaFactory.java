package com.example.lib.designpattern.factory;

public class BeijingPizzaFactory implements StoreFactory {
    @Override
    public Pizza createPizza() {
        return new Pizza("北京偏咸的口味儿");
    }
}
