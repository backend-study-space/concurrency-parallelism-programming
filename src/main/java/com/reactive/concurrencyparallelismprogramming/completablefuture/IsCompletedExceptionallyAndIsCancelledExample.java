package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;

public class IsCompletedExceptionallyAndIsCancelledExample {
    public static void main(String[] args) {

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
//            return 20;
            throw new IllegalArgumentException("error");
        });

//        cf2.cancel(true);

        CompletableFuture<Integer> combinedFuture = cf1.thenCombine(cf2.exceptionally(e -> 15), (r1, r2) -> {
            if (cf2.isCancelled()) {
                return 0;
            } else if (cf2.isCompletedExceptionally()) {
                return r2;
            } else if (cf2.isDone()) {
                return r1 + r2;
            } else return -1;
        });

        Integer result = combinedFuture.join();

        System.out.println("최종 결과 : " + result);
    }
}
