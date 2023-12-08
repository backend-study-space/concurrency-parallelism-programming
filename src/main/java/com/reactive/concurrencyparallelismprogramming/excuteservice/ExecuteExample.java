package com.reactive.concurrencyparallelismprogramming.excuteservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println("비동기 작업 실행");
        });

        // 작업과 실행을 분리.

        executorService.shutdown();

    }
}
