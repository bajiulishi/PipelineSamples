package com.example.lib.designpattern.factory;

public class PizzaStoreImpl extends PizzaStore {

    StoreFactory storeFactory;

    /**
     * 抽象工厂，由外部指定具体实现
     */
    public PizzaStoreImpl(StoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    @Override
    public Pizza createPizza() {
        return storeFactory.createPizza();
    }
}
