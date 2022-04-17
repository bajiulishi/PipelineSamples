package com.example.lib.algorithm.easy;


import static com.example.lib.algorithm.easy.PlusOne.test;

/**
 * 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 *
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 *
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "loveleetcode", c = "e"
 * 输出：[3,2,1,0,1,0,0,1,2,2,1,0]
 * 解释：字符 'e' 出现在下标 3、5、6 和 11 处（下标从 0 开始计数）。
 * 距下标 0 最近的 'e' 出现在下标 3 ，所以距离为 abs(0 - 3) = 3 。
 * 距下标 1 最近的 'e' 出现在下标 3 ，所以距离为 abs(1 - 3) = 2 。
 * 对于下标 4 ，出现在下标 3 和下标 5 处的 'e' 都离它最近，但距离是一样的 abs(4 - 3) == abs(4 - 5) = 1 。
 * 距下标 8 最近的 'e' 出现在下标 6 ，所以距离为 abs(8 - 6) = 2 。
 * 示例 2：
 *
 * 输入：s = "aaab", c = "b"
 * 输出：[3,2,1,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-distance-to-a-character
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ShortestDistanceToACharacter {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.shortestToChar("loveleetcode", 'e'));
        test(solution.shortestToChar("baaab", 'b'));
        test(solution.shortestToChar("aaba", 'b'));

        test(solution.shortestToChar1("loveleetcode", 'e'));
        test(solution.shortestToChar1("baaab", 'b'));
        test(solution.shortestToChar1("aaba", 'b'));
    }

    static class Solution {
        /**
         * 暴力破解：O(n^2)
         */
        public int[] shortestToChar(String s, char c) {
            char[] chars = s.toCharArray();
            int[] res = new int[s.length()];
            for (int index = 0; index < chars.length; index++) {
                if (c == chars[index]) {
                    for (int left = index - 1; left > -1; left--) {
                        if (res[index] == 0 || index - left < res[left]) {
                            res[left] = index - left;
                        }
                    }

                    for (int right = index; right < s.length(); right++) {
                        if (res[index] == 0 || right - index < res[right]) {
                            res[right] = right - index;
                        }
                    }
                }
            }
            return res;
        }

        /**
         * 贪心实现 O(n)
         * <p>
         * 对于每个位置来说就是计算到左侧最近的c字符和到右侧最近的c字符哪个更小
         */
        public int[] shortestToChar1(String s, char c) {
            char[] chars = s.toCharArray();
            final int len = s.length();

            int[] res = new int[len];

            // 当没找到左侧最近的C时，假设一个最大的距离，
            // 保证后面会被右侧C的距离覆盖
            int pre = Integer.MAX_VALUE;

            // 计算每个字符到左侧最近C的距离
            for (int i = 0; i < len; i++) {
                if (c == chars[i]) pre = i;
                res[i] = Math.abs(i - pre);
            }

            // 当没找到右侧的C时，保留之前的值
            pre = Integer.MAX_VALUE;

            // 计算每个字符到右侧最近C的距离，并和左边最近的去最小。
            for (int i = len - 1; i >= 0; i--) {
                if (c == chars[i]) pre = i;
                res[i] = Math.min(res[i], pre - i);
            }
            return res;
        }
    }
}
