package com.example.lib.algorithm.medium;

import java.util.Stack;
/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeString {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.decodeString("3[a]2[bc]"));
        System.out.println(solution.decodeString("3[a10[c]]"));
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
        System.out.println(solution.decodeString("abc3[cd]xyz"));
        System.out.println(solution.decodeString("2[2[2[a]]]"));
    }

    static class Solution {
        Stack<String> stack;

        public String decodeString(String s) {
            stack = new Stack<>();
            final int len = s.length();

            StringBuilder word;
            String item;
            StringBuilder num = null;
            char c;
            for (int i = 0; i < len; i++) {
                c = s.charAt(i);
                if (c == ']') {
                    // 开始回溯，寻找[]中间的字母保存在word中
                    word = new StringBuilder();
                    while (!"[".equals(item = stack.pop())) {

                        // 这里为了保证word和原始一样的顺序。可以优化
                        // 也可以用另外一个栈存储，避免数组拷贝
                        word.insert(0, item);
                    }

                    // 获取循环次数，并对word循环，然后将结果push到栈中
                    StringBuilder temp = new StringBuilder();
                    int n = Integer.parseInt(stack.pop());
                    for (int j = 0; j < n; j++) {
                        temp.append(word);
                    }
                    stack.push(temp.toString());
                } else {
//                    if (c >= '0' && c <= '9') {
                    if (Character.isDigit(c)) {
                        // 如果是数字就将其完整解析出来然后在放入栈中，便于回溯时计算
                        if (num == null) {
                            num = new StringBuilder();
                        }
                        num.append(c);
                    } else {
                        if (num != null) {
                            stack.push(num.toString());
                            num = null;
                        }
                        stack.push(String.valueOf(c));
                    }
                }
            }

            // 从头遍历获取结果
            StringBuilder result = new StringBuilder();
            final int stackLen = stack.size();
            for (int i = 0; i < stackLen; i++) {
                result.append(stack.get(i));
            }
            return result.toString();
        }
    }
}
