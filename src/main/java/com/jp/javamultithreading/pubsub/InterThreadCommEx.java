package com.jp.javamultithreading.pubsub;

/*
    Inter-thread communication in Java is a mechanism in which a thread is paused running in its critical section
    and another thread is allowed to enter (or lock) in the same critical section to be executed.
*/

public class InterThreadCommEx {
    public static void main(String[] args) throws InterruptedException {

        final Play play = new Play();

        // Create a thread object that calls pc.produce()
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    play.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create another thread object that calls
        // pc.consume()
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    play.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }

}
