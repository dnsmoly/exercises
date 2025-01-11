package com.github.dnsmoly.collections;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListDifferences {
    public static void main(String[] args) {
        var arrayList = new ArrayList<Integer>();
        var start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            arrayList.addFirst(i);
        }
        var end = System.nanoTime();
        System.out.println("ArrayList time(ms): " + (end - start) / 1000000);

        var linkedList = new LinkedList<Integer>();
        start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            linkedList.addFirst(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList time(ms): " + (end - start) / 1000000);
    }
}
