package com.jp.javamultithreading.pubsub;

import java.util.LinkedList;

public class Processor {

    LinkedList<Integer> list = new LinkedList<Integer>();
    private final int limit = 10;
    Object lock = new Object();

    public void produce() throws InterruptedException{
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == limit) {
                    lock.wait();
                    System.out.println("producer wait state: ");
                    Thread.sleep(5000);
                }
                list.add(value++);
                lock.notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {

            synchronized (lock) {
                while (list.size() == 0){
                    lock.wait();
                    System.out.println("Consumer in wait state: ");
                    Thread.sleep(5000);
                }
                System.out.println("List size is: "+list.size());
                int value = list.removeFirst();
                System.out.println("; value is: "+value);
                lock.notify();
                Thread.sleep(2000);
            }
        }
    }
}
