package com.example.lib.designpattern.decorator;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Milk(new Mocha(new Espresso()));
        System.out.println("beverage: " + beverage.getDes() + ", cost: " + beverage.cost());

        Beverage beverage1 = new Milk(new HouseBlend());
        System.out.println("beverage: " + beverage1.getDes() + ", cost: " + beverage1.cost());
    }
}
