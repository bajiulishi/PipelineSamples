package com.example.lib.algorithm.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        int length = arr.length;

        //从最后一个非叶节点开始构建大顶堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            maximumHeap(i, arr, length);
        }

        // 此时堆顶(index = 0)就是最大的元素

        //从最小的叶子节点开始与根节点进行交换并重新构建大顶堆
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i); // 将大的元素保存在后面
            length--; // 将排序区减小，有序的区域不需要重新参与排序
            System.out.println(i + ", " + length);
            maximumHeap(0, arr, length);
        }
    }

    // 构建大顶堆
    public static void maximumHeap(int i, int[] arr, int length) {
        int temp = arr[i];
        // 左孩子表示为 i * 2 + 1
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            // 找到最大的孩子
            if (j + 1 < length && arr[j + 1] > arr[j]) {
                j++;
            }
            // 如果最大的孩子大于当前节点，则将大孩子赋给当前节点，并保存修改的位置i
            // 修改当前节点为其大孩子节点，再向下走。
            if (arr[j] > temp) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        // 将temp放到最终位置
        arr[i] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}