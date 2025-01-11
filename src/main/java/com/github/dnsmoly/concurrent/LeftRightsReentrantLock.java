package com.github.dnsmoly.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LeftRightsReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        var signal = lock.newCondition();
        Semaphore semaphore = new Semaphore(1);
        Thread left = new Leg(LegType.LEFT, lock, signal);
        Thread right = new Leg(LegType.RIGHT, lock, signal);
        right.start();
        left.start();

        left.join();
        right.join();
    }

    public static class Leg extends Thread {
        private static final int numberOfSteps = 100;
        private static boolean isLeft = true;

        private final LegType legType;
        private final Lock lock;
        private final Condition signal;

        public Leg(LegType legType, Lock lock, Condition signal) {
            this.legType = legType;
            this.lock = lock;
            this.signal = signal;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfSteps; i++) {
                lock.lock();
                try {

                    if (legType == LegType.RIGHT) {
                        while (isLeft) {
                            try {
                                signal.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    isLeft = !isLeft;
                    if (legType == LegType.RIGHT) {
                        System.out.print(" ".repeat(LegType.LEFT.getType().length() + 1));
                    }
                    System.out.println(legType.getType());
                    signal.signal();
                    if (legType == LegType.LEFT) {
                        while (!isLeft) {
                            try {
                                signal.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

    }

    public enum LegType {
        LEFT("LEFT"), RIGHT("RIGHT");

        private String type;

        LegType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}
