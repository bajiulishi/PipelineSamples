package com.example.lib.designpattern.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Sorter<Cat> sorter = new Sorter<>();

        Cat[] cats = {new Cat(1, 3), new Cat(4, 2), new Cat(2, 1)};
        sorter.selectSort(cats, new CatHeightComparator());
        System.out.println(Arrays.toString(cats));
    }
}
