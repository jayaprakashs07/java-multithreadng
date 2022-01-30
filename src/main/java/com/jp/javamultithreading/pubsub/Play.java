package com.jp.javamultithreading.pubsub;

import java.util.Scanner;

public class Play {

    
    public void produce() throws InterruptedException {

        // synchronized block ensures only one thread running at a time.
        synchronized(this)
        {
            System.out.println("producer is running");

            // releases the lock on shared resource
            wait();

            // waits till some other method invokes notify().
            System.out.println("producer Resumed");
        }
    }

    public void consume() throws InterruptedException {

        // this makes the produce thread to run first.
        Thread.sleep(1000);
        Scanner s = new Scanner(System.in);

        // synchronized block ensures only one thread running at a time.
        synchronized(this)
        {

            System.out.println("Waiting for return key.");
            s.nextLine();
            System.out.println("Return key pressed");

            // notifies the produce thread that it can wake up.
            notify();

            // Sleep
            Thread.sleep(2000);
        }
    }
}
