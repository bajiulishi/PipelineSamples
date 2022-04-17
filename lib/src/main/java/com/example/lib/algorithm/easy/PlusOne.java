package com.example.lib.algorithm.easy;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PlusOne {
    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.plusOne(new int[]{1, 2, 3}));
        test(solution.plusOne(new int[]{4, 9, 9, 9}));
        test(solution.plusOne(new int[]{4, 8, 9, 9}));
        test(solution.plusOne(new int[]{4, 0, 1, 9}));
        test(solution.plusOne(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}));
        test(solution.plusOne(new int[]{0}));
        test(solution.plusOne(new int[]{9}));
    }

    public static void test(int[] array) {
        for (int index = 0; index < array.length; index++) {
            System.out.print(array[index] + " ");
        }
        System.out.println();
    }

    static class Solution {
        public int[] plusOne(int[] digits) {
            final int len = digits.length;
            int[] result = new int[len];
            boolean needAdd = true;
            for (int index = len - 1; index >= 0; index--) {
                if (needAdd) {
                    // 逆序找一个不是9的数字+1，之后的变为0
                    if (digits[index] == 9) {
                        if (index == 0) {
                            int[] rr = new int[len + 1];
                            rr[0] = 1;
                            return rr;
                        }
                        result[index] = 0;
                    } else {
                        needAdd = false;
                        result[index] = digits[index] + 1;
                    }
                } else {
                    result[index] = digits[index];
                }
            }
            return result;
        }
    }
}