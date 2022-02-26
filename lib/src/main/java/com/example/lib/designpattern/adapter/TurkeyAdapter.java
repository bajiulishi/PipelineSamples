package com.example.lib.designpattern.adapter;

/**
 * 让火鸡兼容鸭子的使用
 */
public class TurkeyAdapter implements Duck {

    // 和装饰者模式不同的一个地方是这里的类型
    // 装饰者是和继承的一样的类型，适配者和继承是不一样的
    // 装饰者强调同一类对象的不同包装，适配者强调不同类型的兼容
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 3; i++) {
            turkey.fly();
        }
    }
}
