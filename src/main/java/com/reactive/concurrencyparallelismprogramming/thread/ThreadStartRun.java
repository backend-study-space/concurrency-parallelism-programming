package com.reactive.concurrencyparallelismprogramming.thread;

public class ThreadStartRun {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "스레드 실행");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.setDaemon(true);
        thread.start();

        System.out.println("메인 메서드 종료");
    }
}
