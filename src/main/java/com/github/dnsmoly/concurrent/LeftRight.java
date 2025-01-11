package com.github.dnsmoly.concurrent;

public class LeftRight {
    public static void steps() throws InterruptedException {
        var monitor = new Object();

        var left = new Leg(LegType.LEFT);
        var right = new Leg(LegType.RIGHT);

        left.start();
        right.start();
        left.join();
        right.join();
    }

    public static void main(String[] args) {
        try {
            steps();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Leg extends Thread {
        volatile private static boolean leftStep = true;
        private static final int stepsNum = 100;
        private static final Object lock = new Object();

        private final LegType type;

        Leg(LegType type) {
            this.type = type;
        }

        @Override
        public void run() {
            for (int i = 0; i < stepsNum; i++) {
                synchronized (lock) {
                    if (type == LegType.RIGHT) {
                        while (leftStep) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    System.out.println(type.getName() + " step " + (i + 1));
                    leftStep = !leftStep;
                    lock.notify();

                    if (type == LegType.LEFT) {
                        while (!leftStep) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    private enum LegType {
        LEFT("left"), RIGHT("right");
        private final String name;

        LegType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
