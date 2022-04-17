package com.example.lib.algorithm.medium;

/**
 * 请你设计一个支持下述操作的栈。
 * <p>
 * 实现自定义栈类 CustomStack ：
 * <p>
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * 输出：
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * 解释：
 * CustomStack customStack = new CustomStack(3); // 栈是空的 []
 * customStack.push(1);                          // 栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.push(3);                          // 栈变为 [1, 2, 3]
 * customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
 * customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
 * customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
 * customStack.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
 * customStack.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
 * customStack.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
 * customStack.pop();                            // 返回 -1 --> 栈为空，返回 -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-a-stack-with-increment-operation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DesignAStackWithIncrementOperation {
    public static void main(String[] args) {

        CustomStack1 customStack = new CustomStack1(3); // 栈是空的 []
        customStack.push(1);                          // 栈变为 [1]
        customStack.push(2);                          // 栈变为 [1, 2]
        customStack.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
        customStack.push(2);                          // 栈变为 [1, 2]
        customStack.push(3);                          // 栈变为 [1, 2, 3]
        customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
        customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
        customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
        customStack.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
        customStack.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
        customStack.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
        customStack.pop();                            // 返回 -1 --> 栈为空，返回 -1
    }

    /**
     * 数组实现
     */
    static class CustomStack {
        private int tailIndex = -1;
        private int[] items;

        public CustomStack(int maxSize) {
            items = new int[maxSize];
            print();
        }

        public void push(int x) {
            if (tailIndex < items.length - 1) {
                tailIndex++;
                items[tailIndex] = x;
            }
            print();
        }

        public int pop() {
            if (tailIndex < 0) {
                return -1;
            }

            int r = items[tailIndex];
            items[tailIndex] = 0;
            tailIndex--;
            print();
            return r;
        }

        /**
         * 时间复杂度O(k)
         * 这里还可以优化，可以先把要加的数存到对应的下标中，在获取的时候针再对单个元素累计
         * 如获取下标为3的元素，应该加上下标5，4，3中要累计的数(或者累计前一次即可。在获取5的时候，将前一个4顺便累计)
         */
        public void increment(int k, int val) {
            if (tailIndex >= 0) {
                int max = Math.min(tailIndex, k - 1);
                for (int i = 0; i <= max; i++) {
                    items[i] += val;
                }
            }
            print();
        }

        public void print() {
            System.out.println();
            for (int i = 0; i < items.length; i++) {
                System.out.print(items[i] + " ");
            }
        }
    }


    /**
     * 数组实现
     */
    static class CustomStack1 {
        private int tailIndex = -1;
        private int[] items;
        private int[] incrementItems;

        public CustomStack1(int maxSize) {
            items = new int[maxSize];
            incrementItems = new int[maxSize];
        }

        public void push(int x) {
            if (tailIndex < items.length - 1) {
                tailIndex++;
                items[tailIndex] = x;
            }
        }

        public int pop() {
            if (tailIndex < 0) {
                return -1;
            }

            // 当前当前的元素和自增值
            int r = items[tailIndex] + incrementItems[tailIndex];

            if (tailIndex > 0) {
                // 将当前的自增值保留到前一个上面
                incrementItems[tailIndex - 1] += incrementItems[tailIndex];
            }
            items[tailIndex] = 0;
            // 用完需要重置，避免影响之后同位置的自增
            incrementItems[tailIndex] = 0;
            tailIndex--;
            System.out.println(r);
            return r;
        }

        /**
         * 时间复杂度O(1)，延迟逻辑计算的思想
         * 通过空间incrementItems换时间
         */
        public void increment(int k, int val) {
            int index = Math.min(tailIndex, k - 1);
            if (index >= 0) {
                incrementItems[index] += val;
            }
        }
    }
}
