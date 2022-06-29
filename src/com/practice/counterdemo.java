package com.practice;

import java.util.concurrent.atomic.AtomicInteger;

public class counterdemo {
    public static void main(String[] args) throws InterruptedException {

            Counter c  = new Counter();
            incrementThread incr= new incrementThread(c);
            decrementThread decr= new decrementThread(c);
            incr.start();
            decr.start();
            incr.join();
            decr.join();
        System.out.println(c.getvalue());
    }
}

class decrementThread extends Thread{
    Counter c;

    public decrementThread(Counter c){
        this.c= c;
    }
    public void run(){
        for(int i =0;i<1000;i++ ){
            c.decrement();
        }
    }
}

class incrementThread extends Thread{
    Counter c;

    public incrementThread(Counter c){
        this.c= c;
    }
    public void run(){
        for(int i =0;i<1000;i++ ){
            c.increment();
        }
    }
}

class Counter{
    private AtomicInteger c =new AtomicInteger(0);

    public void increment(){
        c.incrementAndGet();
    }

    public void decrement(){
       c.decrementAndGet();
    }

    public AtomicInteger getvalue(){
        return c;
    }
}
