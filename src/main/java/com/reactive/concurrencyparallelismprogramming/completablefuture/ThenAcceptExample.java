package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
    public static void main(String[] args) {
        MyService service = new MyService();

        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread1: " + Thread.currentThread().getName());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 40;
        }).thenAccept(r -> {
            System.out.println("thread2: " + Thread.currentThread().getName());
            System.out.println("결과 :" + r);
            System.out.println(service.getData1());
        }).thenAcceptAsync(r -> {
            System.out.println("thread3: " + Thread.currentThread().getName());
            System.out.println("결과 :" + r);
            System.out.println(service.getData2());
        }).join();

    }

    static class MyService {
        public int getData1() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 50;
        }

        public int getData2() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 42;
        }
    }
}
