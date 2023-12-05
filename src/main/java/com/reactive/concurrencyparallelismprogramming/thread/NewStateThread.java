package com.reactive.concurrencyparallelismprogramming.thread;

public class NewStateThread {

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("스레드 실행 중");
//            }
//        });
//
//        System.out.println("스레드 상태:" + thread.getState());

//        new RunnableStateThread();
//        new WaitingStateThread();
        new TimedWaitingStateThread();
    }

    static class RunnableStateThread {

        public RunnableStateThread() {
            this.thread.start();
        }

        private Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 1000000000; i++) {
                        if (i % 1000000000 == 0) {
                            System.out.println("스레드 상태: " + Thread.currentThread().getState());
                        }
                    }
                }
            }
        });
    }

    static class WaitingStateThread {

        public WaitingStateThread() throws InterruptedException {
            this.thread.start();
            Thread.sleep(100);
            System.out.println("스레드 상태:" + this.thread.getState());
        }

        final Object lock = new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    static class TimedWaitingStateThread {

        public TimedWaitingStateThread() throws InterruptedException {
            this.thread.start();
            Thread.sleep(100);
            System.out.println("스레드 상태:" + this.thread.getState());
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
