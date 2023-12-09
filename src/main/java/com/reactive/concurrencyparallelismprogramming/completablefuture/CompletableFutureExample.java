package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer result = CompletableFuture.supplyAsync(() -> {
            System.out.println("Service1 시작");
            return 1;
        }).thenApplyAsync(result1 -> {
            System.out.println("Service2 시작");
            return result1 + 2;
        }).thenApplyAsync(result2 -> {
            System.out.println("Service3 시작");
            return result2 * 3;
        }).get();

        System.out.println("결과 : " + result);
    }
}
