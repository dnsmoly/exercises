package com.github.dnsmoly.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownWithSwitch {
    volatile static boolean countAllowed = true;
    volatile static AtomicInteger counter = new AtomicInteger(30);

    public static void main(String[] args) throws InterruptedException {
        var s = new Thread(() -> {
            while (counter.get() > 0) {
                try {
                    Thread.sleep(1000);
                    countAllowed = !countAllowed;
                    System.out.println(LocalDateTime.now() + " | Switch! Count: " + countAllowed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var w = new Thread(() -> {
            while (counter.get() > 0) {
                if (!countAllowed) {
                    continue;
                }
                System.out.println(LocalDateTime.now() + " | Count down: " + counter.getAndDecrement());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        s.start();
        w.start();
        s.join();
        w.join();
    }
}
