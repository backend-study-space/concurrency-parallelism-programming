package com.reactive.concurrencyparallelismprogramming.threadsafe;


public class ThreadSafeLocalReferenceObject {

    LocalObject localObject = new LocalObject();

    static class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject{" + "value=" + value + '}';
        }
    }

    public void useLocalObject() {
//        LocalObject localObject = new LocalObject();

        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObject example = new ThreadSafeLocalReferenceObject();

        new Thread(example::useLocalObject, "Thread-1").start();
        new Thread(example::useLocalObject, "Thread-2").start();
    }
}
