package com.practice;

public class deadlockBetweenThread {
    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();
        Thread t1 = new Thread(new T1(deadlock));
        Thread t2 = new Thread(new T2(deadlock));
        t1.start();
        t2.start();
    }
}

class Deadlock{
    Resource r1 = new Resource();
    Resource r2 = new Resource();

    public void method1(){
        synchronized (r1){
            System.out.println("acquired lock on resource 1 by :"+Thread.currentThread());
            synchronized (r2){
                System.out.println("acquired lock on resource 2 by:"+Thread.currentThread());
            }
        }
    }

    // if we want to break the deadlock then keep the syncronization of acqurire lock in same sequence as other method in same object.
    public void method2(){
        synchronized (r2){
            System.out.println("acquired lock on resource 2 by :"+Thread.currentThread());
            synchronized (r1){
                System.out.println("acquired lock on resoure 1 by :"+Thread.currentThread());
            }
        }
    }
}
class Resource{}

class T1 implements Runnable{

    private Deadlock d1;
    T1(Deadlock d){
        this.d1= d;
    }

    @Override
    public void run() {
        d1.method1();
    }
}


class T2 implements Runnable{

    private Deadlock d2;

    T2(Deadlock d){
        this.d2= d;
    }

    @Override
    public void run() {
        d2.method2();
    }
}