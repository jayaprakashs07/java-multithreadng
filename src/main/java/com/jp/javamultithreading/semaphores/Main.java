package com.jp.javamultithreading.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String args[]) throws Exception {
        //semaphore controls access to a shared resource through the use of a counter.
        /*Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        semaphore.release();
        System.out.println("Semaphore available permits: "+semaphore.availablePermits());*/

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i =0; i<200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }
        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
