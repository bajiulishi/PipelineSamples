package com.example.lib.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个线程往容器中添加10个元素
 * 另外一个线程监控容器大小当大小为5的时候输出
 */
public class WaitNotifySample {
    private static List<Integer> list = new ArrayList<>();
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lock) {
                    if (list.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 唤醒t2继续执行后续的逻辑
                        lock.notify();
                    }
                    System.out.println("list size is 5");
                }
            }
        });
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {

                    synchronized (lock) {
                        list.add(i);
                        System.out.println("add " + i);

                        if (list.size() == 5) {
                            lock.notify();
                            // notify不会释放锁。
                            // 执行到这里，虽然t1被唤醒，但是锁还被t2持有
                            // 需要调用lock.wait释放锁，t1才能获取到锁继续执行
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                System.out.println("t2 " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        t2.start();

    }
}
