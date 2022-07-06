package com.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class InterThreadCommunication {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        ProducerThread prodcureThread = new ProducerThread(queue,"producer1");
        ConsumerThread consumerThread = new ConsumerThread(queue,"consumer1");
        ProducerThread prodcureThread1 = new ProducerThread(queue,"producer2");
        ConsumerThread consumerThread1 = new ConsumerThread(queue,"consumer2");
        prodcureThread.start();
        prodcureThread1.start();
        consumerThread.start();
        consumerThread1.start();
    }
}

class ProducerThread extends Thread{

    private Queue<Integer> que;
    ProducerThread(Queue queueObj,String threadName){
        super(threadName);
        this.que= queueObj;
    }
    public void run(){
        while(true) {
            synchronized (que) {
                while (que.size() == 3) {
                    try {
                        System.out.println(Thread.currentThread().getName()+" queue is full with limits and now waiting for consumer has to consume");
                        que.wait();// if que sizeis 3 then we  have to wait, and it will release the lock from current thread
                        System.out.println("consumer release lock");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int data = random.nextInt();
                que.add(data);
                System.out.println("item aded to bucket queue :"+data);
                que.notifyAll();//it will notify to all waiting thread on current object with  add of  each element  every time , there all sleeping  or waiting thread
            }
        }
    }
}

class ConsumerThread extends Thread{

    private Queue<Integer> queVal;
    ConsumerThread(Queue<Integer> que,String threadName){
        super(threadName);
        queVal= que;
    }
    public void run(){
        while(true) {
            synchronized (queVal) {
                while (queVal.isEmpty()) {
                    try {
                        System.out.println(Thread.currentThread().getName()+" queue is empty  and now waiting for producer  has to produce");
                        queVal.wait();
                            sleep(10000);
                        System.out.println("producer release lock");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("item consumed: "+queVal.remove());
                queVal.notifyAll();
            }
        }
    }

}
