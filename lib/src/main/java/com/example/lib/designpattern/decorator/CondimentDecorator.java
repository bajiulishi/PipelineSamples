package com.example.lib.designpattern.decorator;

public abstract class CondimentDecorator extends Beverage {

    /**
     * 这里为什么要变成抽象方法：为了确保获取完整的描述
     */
    abstract public String getDes();
}
