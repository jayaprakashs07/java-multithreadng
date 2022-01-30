package com.jp.javamultithreading.pubsub;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private volatile int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i=0; i<10000; i++) {
            count++;
        }
    }


    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting ...");
        cond.await(); // same like wait() method it will wait till cond give signal to wake up
        System.out.println("woken up..");
        try {
            increment();
        } finally {
            lock.unlock(); // you have to unlock or-else thread will be in lock mode
        }
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();

        System.out.println("Press the return key.!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key.!");
        cond.signal(); // it worked same like notify() method to wake up the waiting thread

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finishedThread() {
        System.out.println("count is: "+count);
    }

}
