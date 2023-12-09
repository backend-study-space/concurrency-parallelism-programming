package com.reactive.concurrencyparallelismprogramming.completablefuture;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executorService.submit(new Service1());
        Future<Integer> future2 = executorService.submit(new Service2(future1));
        Future<Integer> future3 = executorService.submit(new Service3(future2));

        Integer result = future3.get();

        System.out.println("최종 결과 : " + result);
    }

    private static class Service1 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Service1 시작");
            return 1;
        }
    }

    private static class Service2 implements Callable<Integer> {

        private final Future<Integer> future;

        private Service2(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service2 시작");
            return future.get() + 2;
        }
    }

    private static class Service3 implements Callable<Integer> {

        private final Future<Integer> future;

        private Service3(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service3 시작");
            return future.get() * 2;
        }
    }
}
