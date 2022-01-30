package com.jp.javamultithreading.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchEx {

    public  static void main (String args[]) {

        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executors = Executors.newFixedThreadPool(3);

        for (int i=0; i<3; i++) {
            executors.submit(new Processor(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");

        executors.shutdown();

        System.out.println("All thread are submitted.");

        try {
            executors.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Thread are completed.");
    }


}
