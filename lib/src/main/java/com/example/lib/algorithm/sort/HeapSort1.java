package com.example.lib.algorithm.sort;
import java.util.Arrays;

public class HeapSort1 {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        final int len = arr.length;

        // 非叶子结点构建大顶堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            buildHeap(i, arr, len);
        }

        swap(arr, 0, len - 1);

        // 从叶子结点构建大顶推
        for (int i = len - 2; i >= 0; i--) {
            buildHeap(0, arr, i);
            swap(arr, 0, i);
        }
    }

    private static void buildHeap(int i, int[] array, int length) {
        int temp = array[i];

        for (int j = i * 2 + 1; j <= length; j = j * 2 + 1) {
            if (j + 1 <= length && array[j + 1] > array[j]) {
                j++;
            }

            if (array[j] > temp) {
                array[i] = array[j];
                i = j;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
