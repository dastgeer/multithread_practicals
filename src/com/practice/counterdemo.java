package com.practice;

import java.util.concurrent.atomic.AtomicInteger;

public class counterdemo {
    public static void main(String[] args) throws InterruptedException {

            //Counter c  = new Counter();
        Counter1 c  = new Counter1();
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
    Counter1 c;
    //Counter c;

//    public decrementThread(Counter c){
//        this.c= c;
//    }

    public decrementThread(Counter1 c){
        this.c= c;
    }
    public void run(){
        //c.decrement  currently every time in loop acquire and relase lock so better approach  move for loop to decrement /increment()
        //so 1 time only acquire and release lock. based on suitation we have to do it.
        for(int i =0;i<1000;i++ ){
            c.decrement();
        }
    }
}

class incrementThread extends Thread{
    //Counter c;

    Counter1 c;
//    public incrementThread(Counter c){
//        this.c= c;
//    }
    public incrementThread(Counter1 c){
        this.c= c;
    }
    public void run(){
        //c.decrement  currently every time in loop acquire and relase lock so better approach  move for loop to decrement /increment()
        //so 1 time only acquire and release lock. based on suitation we have to do it.
        for(int i =0;i<1000;i++ ){
            c.increment();
        }
    }
}

class Counter{
    private AtomicInteger c =new AtomicInteger(0);

    //to make synchornized method we have to add synchronized ,only one thread can access at a time ,one thread release then other thread acess
    public  void increment(){
        c.incrementAndGet();
    }

    //to make synchornized method we have to add synchronized ,only one thread can access at a time ,one thread release then other thread acess
    public  void decrement(){
       c.decrementAndGet();
    }

    public AtomicInteger getvalue(){
        return c;
    }
}

class Counter1{
    private int c =0;

    //to make synchornized method we have to add synchronized ,only one thread can access at a time ,one thread release then other thread acess
    public synchronized void increment(){
        c++;
    }

    //to make synchornized method we have to add synchronized ,only one thread can access at a time ,one thread release then other thread acess
    public synchronized void decrement(){
        c--;
    }

    public int getvalue(){
        return c;
    }
}
