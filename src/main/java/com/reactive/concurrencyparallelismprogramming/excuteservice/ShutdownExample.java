package com.reactive.concurrencyparallelismprogramming.excuteservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ShutdownExample {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            Future<Integer> future = executorService.submit(() -> {
                try {
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + ": 작업 종료");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("인터럽트 걸림");
                }

                return 42;
            });
        }

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                System.out.println("스레드 풀 강제 종료");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (executorService.isShutdown()) {
            System.out.println("쓰레드 풀 종료 여부: " + executorService.isShutdown());
        }

        while (!executorService.isTerminated()) {
            System.out.println("스레드 풀 종료 중..");
            Thread.sleep(500);
        }

        System.out.println("모든 작업이 종료되고 스레드 풀이 종료됨.");
    }
}
