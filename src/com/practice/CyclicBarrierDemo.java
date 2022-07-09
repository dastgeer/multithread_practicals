package com.practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3,new CyclicBarrierMaster());
        Thread t1 = new Thread(new Task(barrier));
        Thread t2 = new Thread(new Task(barrier));
        Thread t3 = new Thread(new Task(barrier));
        t1.start();
        t2.start();
        t3.start();///when all thread called await in implementation on cyclic barrier it means they have done there job and wanted to come
        //out of the barrier.once all thread come to barrier then this master class will start executing and internally cyclic barrier
        //give signal to signalAll() so it means get notified and start there further execution. till await() it has to wait for master thread to come
        //onec that executed then it will release all thread for further execution.
        //the term cyclic it means we can reuse same thread for further executions.
    }
}

class CyclicBarrierMaster implements Runnable{

    @Override
    public void run() {
        System.out.println("Thanks you for everyone...");
    }
}

class Task implements Runnable{

    private CyclicBarrier barrier;
    Task(CyclicBarrier barrier){
        this.barrier= barrier;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"I am done with task..");
        try {
            barrier.await();
         //   till here it wil executed first time and in waiting state onec all number of assigned thread in barrier raeach then it will call master thread
            //    master thread and will notifyall/signalAll so all thread resume their further task.
            System.out.println(Thread.currentThread().getName()+"I am doing another   task..");
            barrier.await();
            // again this await will will come to cyclic barrier and wait till all thread come and master thread got exeuted will be holded.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
