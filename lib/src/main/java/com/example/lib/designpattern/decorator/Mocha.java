package com.example.lib.designpattern.decorator;

/**
 * 装饰者实现类，同时也支持被装饰
 * 关键点
 *  1. extends CondimentDecorator
 *  2. Mocha(Beverage beverage)
 */
public class Mocha extends CondimentDecorator {


    // 和适配者模式不同的一个地方是这里的类型
    // 装饰者是和继承的一样的类型，适配者和继承是不一样的
    // 装饰者强调同一类对象的不同包装，适配者强调不同类型的兼容
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDes() {
        return beverage.getDes() + ", 摩卡";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }
}
