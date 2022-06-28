package com.practice;

//when some thread is in sleeping or waiting state other thread can be interuupt itby using notify() or notifyall() to all those thrread
//who is looking for same class instance/object to acquire.
//inrepting thread can be also using interupt() in thread class.
//if we any thread which is sleeping or waiting then some other thread call interupt() on the thread .
public class interuptThreaddemo {
    public static void main(String[] args) {
        childThread chldThread = new childThread();
        chldThread.start();
        chldThread.interrupt();//main thread  execute the call of interuppt() on child thread instance.
        //if child thread which is runing doesnt have gone through sleep() waiting state then it will work as usual execution
        //actaul picture come when chidl thread iis in sleep mode or wait state
        //interuppt() mark the flag ture when it interupt the thread
        System.out.println(chldThread.isInterrupted()); //it will give status true only when it have been interupppted
    }
}

class childThread extends   Thread{

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("child thread :"+i);
           // System.out.println(Thread.interrupted()); //if it was interuppted first time it will give true and if it was to it will toggle true to false state
            //next time it will give state false.
            try {
                System.out.println("into sleep");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("after sleep");
    }
}
