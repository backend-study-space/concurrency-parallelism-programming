package com.reactive.concurrencyparallelismprogramming.thread;

public class MultiThreadSleep {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("1초 후에 메시지가 출력됩니다.");
                Thread.sleep(3000);
                System.out.println("스레드 1이 깨어났습니다.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("2초 후에 메시지가 출력됩니다.");
                Thread.sleep(4000);
                System.out.println("스레드 2이 깨어났습니다.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("여기는 메인입니다.");
    }
}
