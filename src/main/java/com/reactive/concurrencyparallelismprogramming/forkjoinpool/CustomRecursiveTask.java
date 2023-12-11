package com.reactive.concurrencyparallelismprogramming.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private int[] array;
    private int start;
    private int end;

    private static final int THRESHOLD = 2;

    public CustomRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int sum = 0;

            for (int i = start; i < end; i++) {
                sum += array[i];
            }

            return sum;
        } else {
            int middle = start + (end - start) / 2;
            CustomRecursiveTask left = new CustomRecursiveTask(array, start, middle);
            CustomRecursiveTask right = new CustomRecursiveTask(array, middle, end);

            left.fork();
            Integer rightResult = right.compute();
            Integer leftResult = left.join();

            return leftResult + rightResult;
        }
    }
}
