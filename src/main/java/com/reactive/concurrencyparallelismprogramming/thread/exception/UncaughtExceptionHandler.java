package com.reactive.concurrencyparallelismprogramming.thread.exception;

public class UncaughtExceptionHandler {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 시작!");

            throw new RuntimeException("예기치 않은 예외");
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + "에서 예외 발생 : " + e);
        });

        thread.start();
    }
}
