package com.github.dnsmoly.stream;

import java.util.stream.DoubleStream;

public class RandomExamples {
    public static void main(String[] args) {
        DoubleStream s = DoubleStream.of(1.2, 2.4);
        var i = s.peek(System.out::println).filter(x -> x > 2).count();
        System.out.println(i);
    }
}
