package com.github.dnsmoly.concurrent;

import java.util.ArrayList;

public class LeftRightExtended {
    public static void walk() throws InterruptedException {
        var totalLegs = 40;
        var legs = new ArrayList<Leg>(totalLegs);
        for (int i = 0; i < totalLegs; i++) {
            legs.add(new Leg(i, totalLegs));
        }
        for (var leg: legs) {
            leg.start();
        }
        for (var leg: legs) {
            leg.join();
        }
    }

    public static void main(String[] args) {
        try {
            walk();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Leg extends Thread {
        private static volatile int currentLeg = 0;
        private static final int stepsNum = 10;
        private static final Object lock = new Object();

        private final int legNumber;
        private final int totalLegs;

        Leg(int legNumber, int totalLegs) {
            this.legNumber = legNumber;
            this.totalLegs = totalLegs;
        }

        @Override
        public void run() {
            for (int i = 0; i < stepsNum; i++) {
                synchronized (lock) {
                    while (currentLeg != legNumber) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(" ".repeat(legNumber) + legNumber); // add number leading spaces equal to leg number for better visual representation
                    if (++currentLeg == totalLegs) {
                        currentLeg = 0;
                        System.out.println();
                    }
                    lock.notifyAll();
                }
            }
        }
    }
}
