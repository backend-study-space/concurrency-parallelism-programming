package com.reactive.concurrencyparallelismprogramming.excuteservice.hook;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHook extends ThreadPoolExecutor {
    public ThreadPoolExecutorHook(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t.getName() + " 가 곧 작업을 실행합니다.");
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            System.out.println(t.getMessage() + "예외 발생");
        } else {
            System.out.println("작업 성공");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        System.out.println("쓰레드풀이 종료됩니다.");
        super.terminated();
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 1L;

        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(2);

        int taskNum = 6;

        ThreadPoolExecutorHook executor = new ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue
        );

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;

            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 가 테스크 " + taskId + " 를 실행하고 있습니다.");
            });
        }

        executor.shutdown();
    }
}
