package com.practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BLockingQueueDemo {

    static BlockingQueue<Obj> blockingQue = new LinkedBlockingQueue();
    public static void main(String[] args) {
        Thread p1 = new Thread(new producer(blockingQue));
        Thread p2 = new Thread(new producer(blockingQue));
        Thread c1 = new Thread(new consumer(blockingQue));
        Thread c2 = new Thread(new consumer(blockingQue));
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}

class producer implements Runnable{

    private BlockingQueue<Obj> blockQue;

    producer(BlockingQueue que){
        this.blockQue= que;
    }

    @Override
    public void run() {
        for(int i=0;i<=50;i++){
            Obj ob = new Obj(""+i);
            System.out.println(Thread.currentThread().getName()+"producing item:"+ob.getName());
            try {
                blockQue.put(ob);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class consumer implements Runnable{

    private BlockingQueue<Obj> blockQue;

    consumer(BlockingQueue que){
        this.blockQue= que;
    }

    @Override
    public void run() {
        for(int i=0;i<=50;i++){
            try {
                Obj  ob = blockQue.take();
                System.out.println(Thread.currentThread().getName()+"consuming item:"+ob.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}

class Obj{
    private String name;
    Obj(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
