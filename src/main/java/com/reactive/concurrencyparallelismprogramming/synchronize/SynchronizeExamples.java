package com.reactive.concurrencyparallelismprogramming.synchronize;

public class SynchronizeExamples {

    private int instanceCount = 0;

    private static int staticCount = 0;

    public synchronized void instanceMethod() {
        instanceCount++;
        System.out.println("인스턴스 메서드 동기화:" + instanceCount);
    }

    public static synchronized void staticMethod() {
        staticCount++;
        System.out.println("정적 메서드 동기화:" + staticCount);
    }

    public void instanceBlock() {
        synchronized (this) {
            instanceCount++;
            System.out.println("인스턴스 메서드 동기화:" + instanceCount);
        }
    }

    public static void staticBlock() {
        synchronized (SynchronizeExamples.class) {
            staticCount++;
            System.out.println("정적 메서드 동기화:" + staticCount);
        }
    }

    public static void main(String[] args) {
        SynchronizeExamples example = new SynchronizeExamples();

        new Thread(example::instanceMethod).start();
        new Thread(example::instanceBlock).start();
        new Thread(SynchronizeExamples::staticMethod).start();
        new Thread(SynchronizeExamples::staticBlock).start();
    }
}
