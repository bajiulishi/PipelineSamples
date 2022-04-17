package com.example.lib.algorithm.easy;

import java.util.HashMap;

import static com.example.lib.algorithm.easy.PlusOne.test;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        test(solution.twoSum(new int[]{3, 2, 4}, 6));
        test(solution.twoSum(new int[]{3, 3}, 6));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int[] result = {-1, -1};

            HashMap<Integer, Integer> map = new HashMap<>(nums.length);
            int len = nums.length;

            int num;
            for (int i = 0; i < len; i++) {
                num = target - nums[i];

                // 一边检查，一边存map。避免map中重复的key被覆盖
                if (map.containsKey(num)) {
                    result[0] = map.get(num);
                    result[1] = i;
                    break;
                }
                map.put(nums[i], i);
            }
            return result;
        }
    }
}