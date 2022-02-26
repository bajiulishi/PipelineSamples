package com.example.lib.designpattern.singleton;

public class Main {
    public static void main(String[] args) {
//        testSingletonE();
//        testSingletonL();
//        testSingletonL1();
//        testSingletonL2();
        testSingletonHolder();
    }

    private static void testSingletonHolder() {
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonHolder.getInstance());
                }
            }).start();
        }
    }

    private static void testSingletonL2() {
        for (int i = 0; i < 11000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonDoubleCheck.getInstance());
                }
            }).start();
        }
    }

    private static void testSingletonL1() {
        for (int i = 0; i < 11000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonL1.getInstance());
                }
            }).start();
        }
    }


    private static void testSingletonL() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonL.getInstance());
                }
            }).start();
        }
    }

    private static void testSingletonE() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonE.getInstance());
                }
            }).start();
        }
    }
}
