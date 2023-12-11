package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompleteExceptionallyExample {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = new CompletableFuture<>();

        getData(cf1);

        CompletableFuture<String> cf2 = cf1.thenApply(r -> {
            System.out.println(r);
            return r.toUpperCase();
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println("Exception : " + e.getMessage());
                return "noname";
            }

            return r;
        });

        System.out.println("result : " + cf2.join());
    }

    private static void getData(CompletableFuture<String> cf1) {
        try {
            System.out.println("비동기 작업 수행 중");
            Thread.sleep(5000);
            throw new IllegalArgumentException("error");
        } catch (Exception e) {
            cf1.completeExceptionally(e);
        }

        cf1.complete("Hello World");
    }
}
