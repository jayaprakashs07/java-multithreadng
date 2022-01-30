package com.jp.javamultithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolEx {

    public static void main(String args[]) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i=0; i<100; i++) {
            executor.submit(new Test(i));
        }

        executor.shutdown();

        System.out.println("All threads are submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads are completed.");

    }


}
