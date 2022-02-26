package com.example.lib.designpattern.decorator;

/**
 * 饮料的实现类：被装饰者
 */
public class Espresso extends Beverage {

    public Espresso() {
        des = "浓缩咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
