package com.reactive.concurrencyparallelismprogramming.thread.exception;

public class DefaultExceptionHandler {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "에서 예외 발생 " + e);
            }
        });

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드1 예외 발생");
        });

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("스레드2 예외 발생");
        });

        thread1.start();
        thread2.start();
    }
}
