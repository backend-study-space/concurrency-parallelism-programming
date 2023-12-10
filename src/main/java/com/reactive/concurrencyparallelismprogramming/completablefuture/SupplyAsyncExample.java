package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SupplyAsyncExample {
    public static void main(String[] args) {

        CompletableFuture<List<Integer>> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "가 비동기 작업을 시작합니다.");
            try {
                return new MyService().getData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        List<Integer> result = completableFuture.join();

        result.forEach(System.out::println);

        System.out.println("메인스레드 종료");
    }
}

class MyService {
    public List<Integer> getData() throws InterruptedException {
        Thread.sleep(1000);
        return List.of(1, 2, 3);
    }
}
