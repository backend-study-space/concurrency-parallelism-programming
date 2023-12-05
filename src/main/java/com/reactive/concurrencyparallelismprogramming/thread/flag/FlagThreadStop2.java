package com.reactive.concurrencyparallelismprogramming.thread.flag;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStop2 {

    private AtomicBoolean running = new AtomicBoolean(true);
//    private boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("Thread 1 종료. Count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println("Thread 2 종료 중..");
            running.set(false);
        }).start();
    }

    public static void main(String[] args) {
        new FlagThreadStop2().volatileTest();
    }
}
