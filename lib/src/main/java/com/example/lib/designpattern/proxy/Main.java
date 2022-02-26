package com.example.lib.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        // 静态代理：实现同时统计耗时和日志，直接组合使用就好
//        Movable movable = new TankLogProxy(new TankTimeProxy(new Tank()));
//        movable.move();

        // JDK动态代理：适用于接口的实现类代理
        tankJdkDynamicProxy();
    }

    private static void tankJdkDynamicProxy() {
        // 查看动态代理生成文件的开关，以便分析原理
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        final Tank tank = new Tank();

        Movable proxy = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(), new Class[]{Movable.class}, new InvocationHandler(){
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("dynamic start");
                Object res = method.invoke(tank, objects);
                System.out.println("dynamic end");
                return res;
            }
        });
        proxy.move();
    }
}
