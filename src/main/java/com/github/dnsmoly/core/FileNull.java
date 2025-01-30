package com.github.dnsmoly.core;

import java.io.File;

public class FileNull {
    public static void main(String[] args) {
        var file = new File("/home/test.txt");
        if (System.currentTimeMillis() - file.lastModified() > 10_000) {
            System.out.println("File is old. Modified: " + file.lastModified());
        }
    }
}
