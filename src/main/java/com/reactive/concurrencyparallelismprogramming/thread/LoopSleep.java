package com.reactive.concurrencyparallelismprogramming.thread;

public class LoopSleep {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            try {
                System.out.println("1초 후에 메시지가 출력됩니다." + (i + 1));
                Thread.sleep(1000);
                System.out.println("Hello World");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
