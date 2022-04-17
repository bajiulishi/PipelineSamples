package com.example.lib.algorithm.easy;

/**
 * 自创题
 * <p>
 * 给出不同种类的面值，计算1～99种的数字所需的最少的组合
 * <p>
 * 如给出6种不同的货币面额：1，5，10，20，50，100
 * 计算31的话，则输出三张10元，一张一元，一种四张
 */
public class Money {
    public static void main(String[] args) {
        int[] source = {1, 5, 10, 20, 50};
//        int[] source = {1, 5, 10, 20, 50};
//        int[] source = {1, 5, 16, 23, 33};
        excute(source);
    }

    private static void excute(int[] source) {
        final int len = source.length;
        int num;
        StringBuilder solution;
        int total = 0;
        int totalSingle = 0;
        for (int i = 1; i < 100; i++) {
            num = i;
            solution = new StringBuilder();
            totalSingle = 0;
            for (int j = len - 1; j >= 0; j--) {
                if (num >= source[j] && num > 0) {
                    solution.append(source[j] + "*" + num / source[j]);
                    totalSingle += num / source[j];
                    total += num / source[j];
                    num %= source[j];
                    if (num > 0) {
                        solution.append(",");
                    }
                }
            }
            System.out.println(i + ": " + solution.toString() + ", 共：" + totalSingle + "张");
        }
        System.out.println("avg: " + total / 100.0);
    }
}
