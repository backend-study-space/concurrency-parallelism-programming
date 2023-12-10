package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {

        MyService service = new MyService();

        long start = System.currentTimeMillis();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("thread1: " + Thread.currentThread().getName());

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 40;
        }).thenApply(r -> {
            System.out.println("thread2: " + Thread.currentThread().getName());
            int result = service.getData1();
            return r + result;
        })/*.thenApplyAsync(r -> {
            System.out.println("thread3: " + Thread.currentThread().getName());
            int result = service.getData2();
            return r + result;
        })*/;

        Integer finalResult = completableFuture.join();
        System.out.println("총 소요 시간 : " + (System.currentTimeMillis() - start));

        System.out.println("최종 결과 : " + finalResult);
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
