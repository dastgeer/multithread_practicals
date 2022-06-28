package com.practice;

//suppose t1 & t2 are in running state ,t1 want to wait unitl completion of t2,so t1calls join method on itself and pass t2 ,when it
//will do this t2.join() by t1 thent1 will come on waiting state, it wont affect anything to t2 execution ,t2 will carry their execution as it is
// when the execution of t2 got complete and come to dead state then t1 start its executionand continue its execution again and after completion
//of execution of t1 then it will also come to dead state
//join have 3 overloaded method, one is taking long millisecon, t1 is calling thread and t2 called thread if called thread doesnt completes
//its execution with specified amount of time then caller thread t1 resume its execution,and dont wait fro t2 called thread to come and join.
//public void join(long)
public class joindemo {
    public static void main(String[] args) throws InterruptedException {
        //assume our join demo class is supervisor whichis main thread and it will use join on other child thread it means it will wait for all other thread to copmlete and then it
        //will complte its execution.
//as to show demo by setting child thread to join ,it means passing main thread to child thread and child thread also uses join on main thread
        //in that case main thread is waiting for child thread and child thread is waiting for main thread and it become deadlock suitation.

     //   worker5.t= Thread.currentThread(); //to achieve deadlock

        /////////////
        worker5 w5 = new worker5();
        worker6 w6 = new worker6();
        Thread t1 = new Thread(w5);
        Thread t2 = new Thread(w6);
        t1.start();
        t2.start();


        //now use join first  workers thread child thread complete sand then clubs the result and then supriviosor means main thread should start work after this.
        // let those child thread executed and complete their task and then we get the result and show other wise main thread will keep on executing
        //their tasks and other child thread running their task .
        //it is to just make parent/main thread to  synchronous way .from this point

        t1.join();//first it will wait for t1 to join with current main thread
        t2.join();//then it will look for t2 thread to join with main  thread.it means main thread will wait for these to complete and come back.
        System.out.println("main thread as suprisvisor compltes it execution after join all child threads.");
    }

}

class worker5 implements Runnable{

    static Thread t;
    @Override
    public void run() {
        try {
            Thread.sleep(100);
           // t.join();//this line is waiting for  t variable /object to complete task first and come back then this worker5 instance exutes further.,otherwise it will be deadlock state.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("worker 5 build the block");
    }
}

class worker6 implements  Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("worker 6 build the block");
    }
}