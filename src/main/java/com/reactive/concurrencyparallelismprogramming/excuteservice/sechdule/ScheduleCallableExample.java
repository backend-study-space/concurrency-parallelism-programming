package com.reactive.concurrencyparallelismprogramming.excuteservice.sechdule;

import java.util.concurrent.*;

public class ScheduleCallableExample {
    public static void main(String[] args) {

        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);

        Callable<String> task = () -> {
            return "작업이 한 번 실행되고 결과를 반환합니다.";
        };

        ScheduledFuture<String> future = schedule.schedule(task, 3, TimeUnit.SECONDS);

        try {
            String s = future.get();

            System.out.println("s = " + s);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        schedule.shutdown();
    }
}
