package com.example.lib.designpattern.proxy;

/**
 * Movable实现类的日志打印的静态代理
 */
public class TankLogProxy implements Movable {

    /**
     * 针对接口编程：这里用Movable比用Tank有更多的兼容性，比如TankTimeProxy(同时统计日志和耗时)
     * 有点像装饰者模式
     */
    Movable movable;

    public TankLogProxy(Movable movable) {
        this.movable = movable;
    }

    @Override
    public void move() {
        System.out.println("TankLogProxy start");
        movable.move();
        System.out.println("TankLogProxy end");
    }
}
