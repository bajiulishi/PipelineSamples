package com.example.lib;

import java.util.BitSet;

public class TestMain {
    public static void main(String[] args) {
        BitSet s = new BitSet();
        s.set(1);
        s.set(345);
        s.set(2112312123);
        System.out.println(s);
    }
}
