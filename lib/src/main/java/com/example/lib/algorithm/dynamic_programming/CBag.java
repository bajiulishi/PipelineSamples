package com.example.lib.algorithm.dynamic_programming;

/**
 * 经典背包问题
 *
 * 给定w和v两个数组（w和v的长度相同，值均为正数）表示货物的重量和价值
 * 再给定一个背包的容量参数bag
 * 返回在可以装进背包中货物的最大值
 */
public class CBag {

    public static void main(String[] args) {
        int[] w = {3, 2, 4, 7};
        int[] v = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(way1(w, v, bag));
        System.out.println(way2(w, v, bag));
    }

    /**
     * 递归解法
     */
    private static int way1(int[] w, int[] v, int bag) {
        return process1(w, v, 0, bag);
    }

    /**
     * 返回index之后货物（并且可以装进背包）的value最大值
     *
     * @param w：货物的重量
     * @param v：货物的价值
     * @param index：当前下标
     * @param bagRest：背包剩余的空间
     * @return
     */
    private static int process1(int[] w, int[] v, int index, int bagRest) {

        // bag剩余可以等于0的原因是：当bag等于0的时候也可以装东西，比如重量为0的货物
        // 返回-1表示无效，装入背包失败
        if (bagRest < 0) {
            return -1;
        }

        if (index >= w.length) {
            return 0;
        }

        // 不要当前的货
        int exclude = process1(w, v, index + 1, bagRest);

        // 尝试要当前的货
        int include = process1(w, v, index + 1, bagRest - w[index]);
        // 检查装入背包成功，加上当前的value
        if (include != -1) {
            include += v[index];
        }

        // 返回要和不要当前货得到的value值，返回较大的那一个
        return Math.max(exclude, include);
    }


    /**
     * 动态规划的解法
     * 分析变量只有bag和index
     */
    private static int way2(int[] w, int[] v, int bag) {
        int[][] cache = new int[w.length + 1][bag + 1];

        /**
         * if (bagRest < 0) {
         *     return -1;
         * }
         * 这里由于小于0可以忽略
         */

        /**
         * if (index >= w.length) {
         *     return 0;
         * }
         * 表示最后一行值为0，默认就是0，可以忽略
         */

        // 根据递归解法分析，i行数据只依赖i+1行数据，同一行数据没有依赖
        // 先算下面的行
        for (int index = w.length - 1; index >= 0; index--) {

            for (int rest = 0; rest < cache[0].length; rest++) {

                /**
                 * // 不要当前的货
                 * int exclude = process1(w, v, index + 1, bagRest);
                 */
                int exclude = cache[index + 1][rest];

                /*
                 * // 尝试要当前的货
                 * int include = process1(w, v, index + 1, bagRest - w[index]);
                 * // 检查装入背包成功，加上当前的value
                 * if (include != -1) {
                 *     include += v[index];
                 * }
                 */
                int include = rest - w[index] < 0 ? -1 : cache[index + 1][rest - w[index]];
                if (include != -1) {
                    include += v[index];
                }

                /*
                 * // 返回要和不要当前货得到的value值，返回较大的那一个
                 * return Math.max(exclude, include);
                 */
                cache[index][rest] = Math.max(include, exclude);
            }
        }

        /**
         * return process1(w, v, 0, bag);
         */
        return cache[0][bag];
    }
}
