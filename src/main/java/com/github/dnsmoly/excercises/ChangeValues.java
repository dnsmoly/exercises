package com.github.dnsmoly.excercises;

public class ChangeValues {
    static int i = 1;
    static String s = "Value 1";
    static String[] strArr = new String[]{"a"};

    public static void change(int i) {
        i = ++i;
    }

    public static void change(String s) {
        s = "Value 2";
    }

    public static void change(String[] strArr) {
        strArr[0] = "b";
    }

    public static void main(String[] args) {
        change(i);
        System.out.println(i);
        change(s);
        System.out.println(s);
        change(strArr);
        System.out.println(strArr[0]);
    }
}
