package com.jp.javamultithreading.sync;

public class SyncEx1 {

    private int count = 0;

    public static void main(String args[]) {
        SyncEx1 syncEx1 = new SyncEx1();
        syncEx1.runThread();
    }

    public synchronized void increment() {
        count++;
    }

    private void runThread() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("count value is : "+count);

    }

}
