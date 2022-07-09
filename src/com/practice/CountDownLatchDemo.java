package com.practice;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);//it will work properly only with 3 thread because we have given count only 3
        // restrication with that we can use only this when we know the task has to be done by only this much thread.

        Thread t1= new Thread(new Fileservice(latch));
        Thread t2 = new Thread(new Fileservice(latch));
        Thread t3 = new Thread(new Fileservice(latch));

        t2.start();
        t3.start();
        t1.start();

        latch.await();
        System.out.println("countdown latch has been completed...");

    }
}

class Fileservice implements Runnable{

    private final CountDownLatch latchObj;

    Fileservice(CountDownLatch latch){
        latchObj =latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread()+" my file processed");
        latchObj.countDown();//decrement count by 1
    }
}
