package com.reactive.concurrencyparallelismprogramming.threadsafe;

public class ThreadSafeLocalVariable {

//    int localSum = 0;

    public void printNumbers(int plus) {
        int localSum = 0;
//        각 스레드는 이 변수의 독립된 복사본을 가지기 때문에 thread safe함.

        for (int i = 0; i < 5; i++) {
            localSum += i;
            try{
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " - 현재 합계: " + localSum);
    }

    public static void main(String[] args) {
        ThreadSafeLocalVariable example = new ThreadSafeLocalVariable();

        Thread thread1 = new Thread(() -> {
            example.printNumbers(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumbers(40);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
