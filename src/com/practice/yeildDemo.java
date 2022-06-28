package com.practice;

public class yeildDemo {
    public static void main(String[] args) {

        Runnable runnable = new myrunnable();
        Thread t = new Thread(runnable);
        t.start();

        for(int i=0;i<10;i++){
            System.out.println("main thread: "+i);
        }
    }

}

class myrunnable implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("child thread :"+i);
            Thread.yield();// we have strted child thread and main thread is called so child thread running parllely with
            //main thread so order we cannot ppredit.so when we yeild this child thread maximum time main thread will be run.
            //when we yeild then it will come from running state to runnable state and scheduler will give the chance to run to other thread having same priority
            //who has yeild itself.
        }
    }
}