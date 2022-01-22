package com.example.lib.concurrent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模型 condition实现
 * 生产者生产内容，容器满了则等待
 * 消费者消费内容，容器空了则等待
 */
public class ProducerConsumerSample2 {

    static class ProducerConsumer {
        private LinkedList<Integer> linkedList = new LinkedList<>();
        private ReentrantLock lock = new ReentrantLock();

        /**
         * Condition可以理解为不同的等待队列
         */
        private Condition consumerCondition = lock.newCondition();
        private Condition producerCondition = lock.newCondition();

        public void put(Integer num) {
            try {
                lock.lock();

                // 为什么用while不用if：当put被多个线程执行时，
                // 当前线程被唤醒不保证其他的线程是否已经将元素加满，所以唤醒后也需要再次检测
                while (linkedList.size() == 3) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        consumerCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                linkedList.add(num);
                System.out.println(Thread.currentThread().getName() + " put num " + num);

                producerCondition.signalAll();

            } finally {
                lock.unlock();
            }
        }

        public synchronized Integer get() {
            try {
                lock.lock();

                // 为什么用while不用if：当get被多个线程执行时，
                // 当前线程被唤醒不保证其他的线程是否已经将数据消费完，所以唤醒后也需要再次检测
                while (linkedList.size() == 0) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        producerCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Integer num = linkedList.removeFirst();
                System.out.println(Thread.currentThread().getName() + " get num " + num);

                consumerCondition.signalAll();
                return num;
            } finally {
                lock.unlock();
            }
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
                            Thread.sleep(1000);
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
