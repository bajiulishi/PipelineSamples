package com.example.lib.algorithm.dynamic_programming;

/**
 * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2
 * 开始时机器人在其中的start位置上（start一定是1~N中的一个）
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走
 * 规定机器人必须走step步，最终能来到aim位置（aim也是1~N中的一个）的方法有多少种给定四个参数N、start、step、aim，返回方法数。
 * <p>
 * 比如
 * 一共有N=4(1,2,3,4)个位置，机器人初始位置在start=2，目标位置是aim=4，必须走step=4步
 * 那么一共有3种方法，如下
 * 2->3->4->3->4
 * 2->3->2->3->4
 * 2->1->2->3->4
 */
public class ARobotTotalWays {

    public static void main(String[] args) {
//        int start = 2, step = 4, aim = 4, N = 4;
        int start = 2, step = 30, aim = 4, N = 10;
        long time = System.currentTimeMillis();

        System.out.println("普通递归版本 " + way1(start, step, aim, N) + ", cost:" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        System.out.println("递归缓存版本 " + way2(start, step, aim, N) + ", cost:" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        System.out.println("动态规划版本 " + way3(start, step, aim, N) + ", cost:" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();

        System.out.println("参数校验版本 " + way4(2, 1, 5, 10) + ", cost:" + (System.currentTimeMillis() - time));
    }

    /**
     * 常规递归思路解法，根据题意可知
     * 在1位置时只能往2位置走，在N位置时只能往N-1位置走
     * 在中间位置时既可以往左走，又可以往右走
     * 结束条件是需要的步数走完
     *
     * @param start：开始位置
     * @param step：需要走的步数
     * @param aim：目标位置
     * @param N：一共N个位置
     * @return ：有多少种满足上述条件的方法
     */
    public static final int way1(int start, int step, int aim, int N) {

        // 如果走完step步，则返回结果
        if (step == 0) {
            return start == aim ? 1 : 0;
        }

        // 没走完step继续走
        // 先处理边界
        {
            // 边界处理：当在1位置时，只能往2位置走，结果等于在2位置上step-1的结果
            if (start == 1) {
                return way1(2, step - 1, aim, N);
            }

            // 边界处理：当在N位置时，只能往N-1位置走，结果等于在N-1位置上step-1的结果
            if (start == N) {
                return way1(N - 1, step - 1, aim, N);
            }
        }

        // 当在中间位置时，既可以往左走，又可以往右走
        // 最终结果就是往左走的结果 + 往右走的结果
        return way1(start - 1, step - 1, aim, N) + way1(start + 1, step - 1, aim, N);
    }

    /**
     * 优化版本1
     * 普通的递归分解成单个小问题，每走一步都需要重新计算
     * 但是其实之前如果计算过对应位置的结果的话是可以直接复用的
     * 观察递归中只有start和step是在变化，N和aim不变
     * 所以缓存结构用一个二位数据记录start和step
     */
    public static final int way2(int start, int step, int aim, int n) {
        int[][] cache = new int[n + 1][step + 1];
        // 填充初始化数据，来和缓存数据做区分
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                cache[i][j] = -1;
            }
        }

        return procced2(start, step, aim, n, cache);
    }

    public static final int procced2(int start, int step, int aim, int N, int[][] cache) {

        // 有缓存的话直接返回缓存的内容即可
        if (cache[start][step] != -1) {
            return cache[start][step];
        }

        // 没有缓存则计算，然后并将结果缓存
        int result;
        if (step == 0) {
            result = start == aim ? 1 : 0;
        } else if (start == 1) {
            result = procced2(2, step - 1, aim, N, cache);
        } else if (start == N) {
            result = procced2(N - 1, step - 1, aim, N, cache);
        } else {
            result = procced2(start - 1, step - 1, aim, N, cache) + procced2(start + 1, step - 1, aim, N, cache);
        }

        // 缓存
        cache[start][step] = result;

        return result;
    }

    /**
     * 使用动态规划优化版本（数字规律）
     * 尽管给递归加了缓存，减少了递归的次数，不过总的来说还是有不少的递归
     * 想办法更具题意和条件找：数 字 规 律，将结果直接填入缓存数组中（https://www.bilibili.com/video/BV1ET4y1U7T6?p=4）
     */
    public static final int way3(int start, int step, int aim, int n) {

        // 横行代表当前的位置，纵行代表要走的步数
        int[][] cached = new int[n + 1][step + 1];

        /**
         * if (step == 0) {
         *     return start == aim ? 1 : 0;
         * }
         * 根据条件可知第0列，除了第aim行是1其余是0
         */
        cached[aim][0] = 1;


        for (int j = 1; j < cached[0].length; j++) {

            /**
             * if (start == 1) {
             *     return way1(2, step - 1, aim, N);
             * }
             * 第1行的结果依赖第2行,第step-1列的结果，在二维数组中的位置就是左下角
             */
            cached[1][j] = cached[2][j - 1];

            // start为1和N时，单独计算
            for (int i = 2; i < cached.length - 1; i++) {

                /**
                 * way1(start - 1, step - 1, aim, N) + way1(start + 1, step - 1, aim, N);
                 * 中间位置的结果= (第start-1行，第step-1列的结果) + (第start+1行，第step-1列的结果)
                 * 在二维数组中的位置就是（左上）+（左下）
                 */
                cached[i][j] = cached[i - 1][j - 1] + cached[i + 1][j - 1];
            }

            /**
             * if (start == N) {
             *     return way1(N - 1, step - 1, aim, N);
             * }
             * 第N行的结果依赖第N-1行，step减1列，在二维数组中的位置就是左上角
             */
            cached[n][j] = cached[n - 1][j - 1];
        }

        return cached[start][step];
    }


    /**
     * 健壮性优化：参数校验
     */
    public static final int way4(int start, int step, int aim, int n) {
        if (start < 0 || start > n ||
                step < 0 ||
                aim < 0 || aim > n ||
                step < Math.abs(aim - start)
        ) {
            return -1;
        }

        int[][] cached = new int[n + 1][step + 1];

        cached[aim][0] = 1;

        for (int j = 1; j < cached[0].length; j++) {
            cached[1][j] = cached[2][j - 1];

            for (int i = 2; i < cached.length - 1; i++) {
                cached[i][j] = cached[i - 1][j - 1] + cached[i + 1][j - 1];
            }

            cached[n][j] = cached[n - 1][j - 1];
        }

        return cached[start][step];
    }
}
