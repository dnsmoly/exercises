package com.github.dnsmoly.core;

public class StackOverflowTrigger {
    public static void main(String[] args) {
        int i = fillStack(0);
    }

    public static int fillStack(int n) {
        return fillStack(n + 1);
    }
}
