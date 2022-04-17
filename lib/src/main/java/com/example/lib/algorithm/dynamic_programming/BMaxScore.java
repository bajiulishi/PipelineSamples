package com.example.lib.algorithm.dynamic_programming;

/**
 * 给定一个数组，其中的数值代表分数
 * A和B分别先后从最左边或者最右边获取值
 * A先拿，A和B都很聪明
 * 返回获胜的分数
 * <p>
 * 比如 array = {50, 100, 20, 10}
 * A先拿10，如果拿了50，则聪明B就会选择100，然后B就胜利了，所以不能先拿50
 * B拿的时候只有{50, 100, 20}，不管拿哪个，100都会被A拿走，所以B会选相对高的50
 * 然后A拿100，B拿20
 * 最终的分数A:10+100, B:50+20，最终返回110
 */
public class BMaxScore {

    public static void main(String[] args) {
//        int[] array = {50, 100, 20, 10};
//        int[] array = {1,100,1};
        int[] array = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};

        System.out.println(winScore1(array));
        System.out.println(winScore2(array));
        System.out.println(winScore3(array));
    }

    public static final int winScore1(int[] array) {
        int first = first1(array, 0, array.length - 1);
        int second = sencond1(array, 0, array.length - 1);

        // 最终胜利的分：先拿和后拿的分数取最大值
        return Math.max(first, second);
    }

    /**
     * 先手在array的left~right拿
     * 返回尽可能拿到最高的分数
     */
    public static final int first1(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        }

        // 第一种选择：先拿左边的，然后加上后拿的分数
        int resul1 = array[left] + sencond1(array, left + 1, right);

        // 第二种选择：先拿右边的，然后加上后拿的分数
        int resul2 = array[right] + sencond1(array, left, right - 1);

        // 由于是先手，能选当然拿更大的值
        return Math.max(resul1, resul2);
    }

    /**
     * 返回后手在array的left~right拿到的分数
     */
    public static final int sencond1(int[] array, int left, int right) {

        // 当前只有一个元素，所以后手就拿不到了
        if (left == right) {
            return 0;
        }

        // 后手可以拿到什么值取决于对方怎么选
        // 对方此时是先手，由于足够聪明所以也会尽可能取获取做大的值所以调用first函数
        // 对方拿走了左边的数的情况
        int resul1 = first1(array, left + 1, right);

        // 对方拿走了右边的数的情况
        int resul2 = first1(array, left, right - 1);

        // 由于是后手，只能拿到最小的值，最大的值会被对方先手拿走
        return Math.min(resul1, resul2);
    }


    /**
     * 增加缓存优化递归
     * 根据递归分析，变量是f/g, left, right
     * f/g可以维护两张表，每张表里保存left和right
     */
    public static final int winScore2(int[] array) {
        int length = array.length;
        int[][] fCache = new int[length][length];
        int[][] gCache = new int[length][length];
        // 初始化填充，以区别真实的结果
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                fCache[i][j] = -1;
                gCache[i][j] = -1;
            }
        }

        int first = first2(array, 0, array.length - 1, fCache, gCache);
        int second = second2(array, 0, array.length - 1, fCache, gCache);
        return Math.max(first, second);
    }

    public static final int first2(int[] array, int left, int right, int[][] fCache, int[][] gCache) {

        if (fCache[left][right] != -1) {
            return fCache[left][right];
        }

        int result;
        if (left == right) {
            result = array[left];
        } else {
            int resul1 = array[left] + second2(array, left + 1, right, fCache, gCache);
            int resul2 = array[right] + second2(array, left, right - 1, fCache, gCache);
            result = Math.max(resul1, resul2);
        }
        fCache[left][right] = result;
        return result;
    }

    public static final int second2(int[] array, int left, int right, int[][] fCache, int[][] gCache) {

        if (gCache[left][right] != -1) {
            return gCache[left][right];
        }

        int result;
        if (left == right) {
            result = 0;
        } else {
            int resul1 = first2(array, left + 1, right, fCache, gCache);
            int resul2 = first2(array, left, right - 1, fCache, gCache);
            result = Math.min(resul1, resul2);
        }
        gCache[left][right] = result;
        return result;
    }


    /**
     * 通过题意，画图，找数字的规律来取代递归
     * <p>
     * fcache填充gcache
     * gcache填充fcache
     * <p>
     * 由于不存在left > right
     * 所以可以忽略二维数组对角线的下方全部位置
     */
    public static final int winScore3(int[] array) {
        int length = array.length;
        int[][] fCache = new int[length][length];
        int[][] gCache = new int[length][length];

        // 初始化
        for (int i = 0; i < length; i++) {

            /**
             * if (left == right) {
             *     return array[left];
             * }
             * 先手的缓存的对角线为对应下标的数值
             */
            fCache[i][i] = array[i];

            /**
             * if (left == right) {
             *     return 0;
             * }
             * 后手的缓存的对角线为0，由于默认也是0，所以这步可省略，此处好理解先保留
             */
            gCache[0][0] = 0;
        }

        /**
         * 填充每一条对角线
         */
        for (int i = 1; i < length; i++) {
            int left = 0; // row
            int right = i; // column
            while (right < length) {

                /**
                 * int resul1 = first1(array, left + 1, right);
                 * int resul2 = first1(array, left, right - 1);
                 * return Math.min(resul1, resul2);
                 * gCache依赖fCache，由fCache的 min((left + 1)(对应点下一个),(right - 1)(对应点左一个))来填充
                 */
                gCache[left][right] =
                        Math.min(fCache[left + 1][right],
                                fCache[left][right - 1]);

                /**
                 * int resul1 = array[left] + sencond1(array, left + 1, right);
                 * int resul2 = array[right] + sencond1(array, left, right - 1);
                 * return Math.max(resul1, resul2);
                 * fCache依赖gCache，由gCache的 max(array[left] + (left + 1)(对应点下一个),
                 *          array[right] + (right - 1)(对应点左一个))来填充
                 */
                fCache[left][right] =
                        Math.max(array[left] + gCache[left + 1][right],
                                array[right] + gCache[left][right - 1]);

                left++;
                right++;
            }
        }

        /**
         * int first = first2(array, 0, array.length - 1, fCache, gCache);
         * int second = second2(array, 0, array.length - 1, fCache, gCache);
         * return Math.max(first, second);
         */
        return Math.max(fCache[0][length - 1], gCache[0][length - 1]);
    }
}
