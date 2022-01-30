package com.jp.javamultithreading.semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

    private static Connection instance = new Connection();
    private int connections = 0;
    private Semaphore semaphore = new Semaphore(10, true); // if give true it will not leave thread background

    private Connection() {

    }

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static Connection getInstance() {
        return instance;
    }

    public  void doConnect() throws InterruptedException {
        synchronized (this) {
            connections++;
            System.out.println("Current connections : "+connections);
        }

        Thread.sleep(2000);

        synchronized (this) {
            connections--;
        }
    }
}
