package com.example.lib.designpattern.decorator;

/**
 * 饮料的实现类：被装饰者
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        des = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
