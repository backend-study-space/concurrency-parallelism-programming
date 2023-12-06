package com.reactive.concurrencyparallelismprogramming.sync;

public class CpuSync {

    private static int count = 0;

    private static final int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSync.class) {
                    count++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (CpuSync.class) {
                    count++;
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(count);
        System.out.println(ITERATIONS * 2);
    }
}
