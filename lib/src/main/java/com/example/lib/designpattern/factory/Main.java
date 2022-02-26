package com.example.lib.designpattern.factory;

public class Main {
    public static void main(String[] args) {
//        PizzaStore pizzaStore = new PizzaStoreImpl(new SuzhouPizzaFactory());
//        pizzaStore.createPizza();

        PizzaStore pizzaStore = new PizzaStoreImpl(new BeijingPizzaFactory());
        pizzaStore.createPizza();
    }
}
