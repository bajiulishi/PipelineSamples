package com.example.lib.concurrent;

import java.util.LinkedList;

/**
 * 生产者消费者模型 synchronized实现
 * 生产者生产内容，容器满了则等待
 * 消费者消费内容，容器空了则等待
 */
public class ProducerConsumerSample1 {


    static class ProducerConsumer {
        private LinkedList<Integer> linkedList = new LinkedList<>();

        public synchronized void put(Integer num) {
            // 为什么用while不用if：当put被多个线程执行时，
            // 当前线程被唤醒不保证其他的线程是否已经将元素加满，所以唤醒后也需要再次检测
            while (linkedList.size() == 10) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            linkedList.add(num);
            System.out.println(Thread.currentThread().getName() + " put num " + num);

            // 这个里会把消费者和生产者都唤醒，有瑕疵。
            // 理论上来讲只需要唤醒消费者线程即可，可以用Condition优化
            notifyAll();
        }

        public synchronized Integer get() {
            // 为什么用while不用if：当get被多个线程执行时，
            // 当前线程被唤醒不保证其他的线程是否已经将数据消费完，所以唤醒后也需要再次检测
            while (linkedList.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            Integer num = linkedList.removeFirst();
            System.out.println(Thread.currentThread().getName() + " get num " + num);

            // 这个里会把消费者和生产者都唤醒，有瑕疵。
            // 理论上来讲只需要唤醒生产者线程即可，可以用Condition优化
            notifyAll();
            return num;
        }
    }

    public static void main(String[] args) {

        final ProducerConsumer producerConsumer = new ProducerConsumer();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        producerConsumer.put(22);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "putThread" + i).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        producerConsumer.get();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "getThread" + i).start();
        }
    }
}
