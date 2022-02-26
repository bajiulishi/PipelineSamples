package com.example.lib.designpattern.strategy;

public class Sorter<T> {

    /**
     * 用选择排序做示范
     */
    public void selectSort(T[] array, Comparator<T> comparator) {

        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                min = comparator.compare((T) array[j], array[min]) == -1 ? j : min;
            }
            swap(array, i, min);
        }
    }

    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
