package com.example.lib.designpattern.decorator;

/**
 * 饮料基类，相当于Component
 */
public abstract class Beverage {
    protected String des = "unknown";

    public String getDes() {
        return des;
    }

    public abstract double cost();
}
