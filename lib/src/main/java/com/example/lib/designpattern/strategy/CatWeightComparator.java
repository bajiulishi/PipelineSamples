package com.example.lib.designpattern.strategy;

public class CatWeightComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o, Cat n) {
        if(o.weight > n.weight) return 1;
        else if(o.weight < n.weight) return -1;
        else return 0;
    }
}
