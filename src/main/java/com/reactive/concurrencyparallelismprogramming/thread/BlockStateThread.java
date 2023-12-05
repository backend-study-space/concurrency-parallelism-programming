package com.reactive.concurrencyparallelismprogramming.thread;

public class BlockStateThread {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {

                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("락을 획득하려고 함");
                }
            }
        });
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread2.getState());
    }
}
