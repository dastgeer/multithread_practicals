package com.practice;

//2 ways of creating thread
// 1. using thread class
//2. using runnable interface
public class CreateThread {
    public static void main(String[] args) {
//-------------------
//        worker1 w1= new worker1();
//        worker2 w2= new worker2();
//        w1.method1();//this way both of them run sequenctially because main thread first execute this complete this and then go next step
//        w2.method1();//then main thread come to object and execute
        //-------------------------------- extending thread class-----------
//        worker1 w1= new worker1();//initialization of thread1
//        worker2 w2= new worker2();//initialization of thread 2
//        //start method  interanally create the thread and  start the using start() and run the task given to it by starting with assigned in run()
//       //both of them are chld thread of main thread so the task assigned to each one will be run parallely doesnt depend on each other or main thread.
//        //so we can see output in random order from thread 1 as well as thread 2  bcz running parallely.
//        w1.start();
//        w2.start();

        //-------------------------------- implenting  runnable-----------
      //  thread class also interannly implmemts runnable interface and internnaly override  run() with our interface implemented run()

        worker3 w3 = new worker3();
        worker4 w4 = new worker4();
        Thread t1 = new Thread(w3);//thread class constructor accpt runnable and to startthread we need start() and only thread class have start()
        Thread t2 = new Thread(w4);//so we have created thread and passed to it and we will call thread instance to start thread and run method will be called internally and task get executed
        t1.start();//sepratelya nd parallely to each other
        t2.start();

        //if we call run() directly of below worker class then it will work as normal method of object not thread run() method becase
        //it has not created thread and started thread to run sepreatly.

    }
}
// example1: this is for simple implemetation
//class worker1{
//    public void method1(){
//        for(int i=0;i<10;i++){
//            System.out.println("print worker1 :"+i);
//        }
//    }
//}
//
//class worker2{
//    public void method1(){
//        for(int i=0;i<10;i++){
//            System.out.println("print worker2 :"+i);
//        }
//    }
//}

//example2: by extending thread class an dand thread class have run() to run any task as thread
class worker1 extends Thread{
    //this run method containe that task which actaully want to run
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("print worker1 :"+i);
        }
    }
}

class worker2 extends Thread{
    //this run method containe that task which actaully want to run
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("print worker2 :"+i);
        }
    }
}

//example3: by implementing runnable interface and have run() to run any task as thread
class worker3 implements Runnable {
    //this run method containe that task which actaully want to run
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("print worker1 :"+i);
        }
    }
}

class worker4 implements Runnable{
    //this run method containe that task which actaully want to run
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("print worker2 :"+i);
        }
    }
}

