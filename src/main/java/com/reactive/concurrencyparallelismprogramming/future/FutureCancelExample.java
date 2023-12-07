package com.reactive.concurrencyparallelismprogramming.future;

import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callable = () -> {
            System.out.println("비동기 작업 시작");
            Thread.sleep(3000);
            System.out.println("비동기 작업 완료");

            return 42;
        };

        Future<Integer> future = executorService.submit(callable);

        Thread.sleep(1000);
        future.cancel(true);
//        future.cancel(false);

        // 인터럽트를 걸면 쓰레드에서 바로 예외가 터짐

        while (!future.isDone()) {
            System.out.println("작업을 기다리는 중..");
            Thread.sleep(500);
        }

        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
