package com.reactive.concurrencyparallelismprogramming.excuteservice.rejectpolicy;

import java.util.concurrent.*;

public class AbortPolicyExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0L;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(workQueueCapacity),
                new ThreadPoolExecutor.AbortPolicy()
        );

        submitTasks(executor);
    }

    private static void submitTasks(ThreadPoolExecutor executor) {
        for (int i = 0; i <= 5; i++) {
            final int taskId = i;
            try {
                executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + " 가 테스크 " + taskId + " 를 실행하고 있습니다.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (RejectedExecutionException e) {
                executor.shutdown();
                // submit에서 호출하는 reject 메서드에서 예외가 발생하기 때문에 try catch로 잡아줘야함.
            }
        }
    }
}
