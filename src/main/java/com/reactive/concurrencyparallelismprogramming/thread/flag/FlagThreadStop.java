package com.reactive.concurrencyparallelismprogramming.thread.flag;

public class FlagThreadStop {

//    private volatile static boolean running = true;
    private static boolean running = true;
    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            while(running) {
                try {
                    Thread.sleep(1);
                    // 컨텍스트 스위칭이 일어날 때, CPU 캐시가 비워진 후 저장됨.
                    // volatile 변수를 사용해서 캐시가 아닌 메모리에서 값을 가져올 수 있음.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count ++;
            }
            System.out.println("스레드 1 종료, count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드 2 종료");
            running = false;
        }).start();
    }
}
