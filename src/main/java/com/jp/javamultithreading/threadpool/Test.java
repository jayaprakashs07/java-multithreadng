package com.jp.javamultithreading.threadpool;

public class Test implements Runnable {

    public int id;

    public Test(int id) {
        this.id=id;
    }

    @Override
    public void run() {
        System.out.println("Starting ID = "+id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ending ID = "+id);
    }
}
