package com.reactive.concurrencyparallelismprogramming.excuteservice.sechdule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        Runnable task = () -> {
            try {
                Thread.sleep(500);
                System.out.println("Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);

        future.cancel(true);
        scheduledExecutorService.shutdown();
    }
}
