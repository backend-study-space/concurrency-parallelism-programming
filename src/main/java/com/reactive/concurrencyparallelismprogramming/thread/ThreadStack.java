package com.reactive.concurrencyparallelismprogramming.thread;

public class ThreadStack {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new TestRunnable(i));
            thread.start();
        }
    }

    static class TestRunnable implements Runnable {
        private final int threadId;

        public TestRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 실행 중");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
            String objectReference = threadId + ": Hello";
            System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value:" +  localValue);
        }
    }
}
