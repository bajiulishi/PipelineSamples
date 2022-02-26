package com.example.lib.designpattern.proxy;

public class Tank implements Movable {
    @Override
    public void move() {
        System.out.println("tank start moving");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 1. 静动源码实现，cglib不需要接口不支持final的类，底层都是asm修改字节码
        // 2. 原理解读
    }
}
