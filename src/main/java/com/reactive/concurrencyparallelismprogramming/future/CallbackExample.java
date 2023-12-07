package com.reactive.concurrencyparallelismprogramming.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {

    interface Callback {
        void onComplete(int result);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }

            Callback callback = a -> System.out.println("비동기 작업 결과: " + a);

            callback.onComplete(42);

        });

        System.out.println("비동기 작업 시작");
    }
}
