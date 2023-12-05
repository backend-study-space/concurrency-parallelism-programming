package com.reactive.concurrencyparallelismprogramming.thread.group;

public class ThreadGroup {
    public static void main(String[] args) {
        java.lang.ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        java.lang.ThreadGroup customThreadGroup = new java.lang.ThreadGroup("Custom Thread Group");

        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");

        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");

        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "CustomGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();

    }

    static class GroupRunnable implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + thread.getThreadGroup().getName());
        }
    }
}
