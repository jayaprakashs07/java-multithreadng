package com.jp.javamultithreading.deadlock;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
        while (true) {
            // Acquire lock
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {

                if (gotFirstLock && gotSecondLock) {
                    return;
                }

                if (gotFirstLock) {
                    firstLock.unlock();
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }
            }
            Thread.sleep(100);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i< 1000; i++) {
//            lock1.lock();
//            lock2.lock();
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i< 1000; i++) {
//            lock2.lock();
//            lock1.lock(); // dead lok will occur to avoid the deadlock we can use acquirelock method
            acquireLocks(lock2, lock1);
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finishedThread() {
        System.out.println("Account 1 balance is: "+acc1.getBalance());
        System.out.println("Account 2 balance is: "+acc2.getBalance());
        System.out.println("Total balance is: "+ (acc1.getBalance()+acc2.getBalance()));
    }
}
