package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteExample {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                throw new RuntimeException("error");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return 10;
        }).whenComplete((r, e) -> {
            if (e != null) {
                System.out.println("Exception : " + e.getMessage());
            } else {
                System.out.println("result : " + r);
            }
        });

        Thread.sleep(2000);

        // 예외 처리를 해줘야함.
        System.out.println(cf.join());
    }
}
