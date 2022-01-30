package com.jp.javamultithreading.futureAndCallback;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class Example1 {
    public static void main(String args[]) {

        ExecutorService executor = Executors.newCachedThreadPool();

        // normal code
        /*executor.submit(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                int duration = random.nextInt(4000);
                System.out.println("Starting..");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Ending..");
            }
        });*/

        // we can use wild card if dont want to return value
        // like this Future<?> future = executor.submit(new Callable<void>()

        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000);
                System.out.println("Starting..");

                if (duration > 2000) {
                    throw new IOException("Sleeping is too long");
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Ending..");
                return duration;
            }
        });

        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.DAYS);
        try {
            System.out.println("Result is : "+ future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            IOException ex = (IOException) e.getCause();
            System.out.println(ex.getMessage());
        }

    }
}
