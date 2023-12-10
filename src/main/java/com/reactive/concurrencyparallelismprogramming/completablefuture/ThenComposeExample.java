package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenComposeExample {
    public static void main(String[] args) {

        MyService service = new MyService();

        CompletableFuture<Integer> cf1 = service.getData1(5); // cf1

        CompletableFuture<Double> cf2 = cf1.thenCompose(input -> { // cf2
            System.out.println("thread : " + Thread.currentThread().getName());
            return service.getData2(input); // cf 3
        });

        Double result = cf2.join();
        System.out.println("최종 결과 : " + result);
    }

    static class MyService {

        public CompletableFuture<Integer> getData1(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return input * 2;
            });
        }

        public CompletableFuture<Double> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return input * 2.0;
            });
        }
    }
}
