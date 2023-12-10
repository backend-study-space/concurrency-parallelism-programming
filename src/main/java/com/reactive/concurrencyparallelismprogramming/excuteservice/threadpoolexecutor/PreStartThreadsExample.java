package com.reactive.concurrencyparallelismprogramming.excuteservice.threadpoolexecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PreStartThreadsExample {
    public static void main(String[] args) {

        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(4);

        int taskNum = 9;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        executor.prestartCoreThread();
        // 쓰레드 하나만 생성, executor.execute 실행 전에 쓰레드가 미리 생성 됨.

        executor.prestartAllCoreThreads();
        // 설정한 모든 갯수만큼 생성

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 테스크" + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();
    }
}
