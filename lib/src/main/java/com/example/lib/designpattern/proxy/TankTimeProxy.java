package com.example.lib.designpattern.proxy;

/**
 * Movable实现类的耗时记录的静态代理
 */
public class TankTimeProxy implements Movable {
    /**
     * 针对接口编程：这里用Movable比用Tank有更多的兼容性，比如TankLogProxy(同时统计日志和耗时)
     * 有点像装饰者模式
     */
    Movable movable;

    public TankTimeProxy(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        movable.move();
        long end = System.currentTimeMillis();
        System.out.println("TankTimeProxy cost: " + (end - start));
    }
}
