package com.practice;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    Semaphore semaphore = new Semaphore(4);// so only one thread have permit to acess resource. by one thread at atime.
    public static void main(String[] args) {
        SemaphoreDemo demo= new SemaphoreDemo();
         new Thread(){
             public void run(){
                     demo.mutualExclusion();
             }
         }.start();

        new Thread(){
            public void run(){

                    demo.mutualExclusion();

            }
        }.start();
    }

    //only one thread allowed to acquire permit,whichever thread come first will be acquire permit first and then that release permit then
    // it will release lock.
    private void mutualExclusion() {
        try {
            semaphore.acquire();// if permit will be available then thread will access resource. else wait for permit or permit count to relase
            System.out.println(Thread.currentThread().getName() + ":inside mutual exclusive region");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            semaphore.release();
            System.out.println(Thread.currentThread().getName()+":outside mutual exclusive region");
        }
    }
}
