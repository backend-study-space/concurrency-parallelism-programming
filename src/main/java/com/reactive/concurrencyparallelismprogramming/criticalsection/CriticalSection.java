package com.reactive.concurrencyparallelismprogramming.criticalsection;

public class CriticalSection {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread t1 = new Thread(sharedResource::increment);
        Thread t2 = new Thread(sharedResource::increment);

        t1.start();
        t2.start();
    }
}

class SharedResource {
    private int counter = 0;
    
    public void increment() {
        for (int i = 0; i < 100000; i++) {
            synchronized (this) {
                counter++;
                System.out.println(Thread.currentThread().getName() + ": " + counter);
            }
        }
        
        doOtherWork();
    }

    private void doOtherWork() {
        System.out.println(Thread.currentThread().getName() + " 는 critical section 외부에서 작업을 수행하고 있습니다.");
    }

    public int getCounter() {
        return counter;
    }
}