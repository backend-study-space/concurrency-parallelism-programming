package com.reactive.concurrencyparallelismprogramming.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CustomForkJoinPoolExample {
    public static void main(String[] args) {
        int[] array = new int[4];

        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask<Integer> task = new CustomRecursiveTask(array, 0, array.length);

        Integer result = pool.invoke(task);

        System.out.println(result);
    }

}
