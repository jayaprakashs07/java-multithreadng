package com.jp.javamultithreading.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void blockOne() {
        //synchronization to lock the one object for the one thread

        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void blockTwo() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void process() {

        for (int i =0; i<1000;i++) {
            blockOne();
            blockTwo();
        }
    }

    public void main() {
        System.out.println("Start ");
        long start = System.currentTimeMillis();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("time take = "+ (end-start));
        System.out.println("List1 = "+list1.size()+ " List2 = "+list2.size());
    }
}
