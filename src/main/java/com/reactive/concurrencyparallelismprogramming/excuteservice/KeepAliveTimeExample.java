package com.reactive.concurrencyparallelismprogramming.excuteservice;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeepAliveTimeExample {
    public static void main(String[] args) throws InterruptedException {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 1L;

        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(2);

        int taskNum = 6;

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;

            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 테스크 " + taskId + " 를 실행하고 있습니다.");
            });
        }

        Thread.sleep(4000);
        threadPoolExecutor.shutdown();
    }
}
