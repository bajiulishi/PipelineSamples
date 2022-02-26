package com.example.lib.designpattern.strategy;

public class CatHeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o, Cat n) {
        if(o.height > n.height) return 1;
        else if(o.height < n.height) return -1;
        else return 0;
    }
}
