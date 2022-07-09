package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ExecutorDemo {
    public static void main(String[] args) {
       ExecutorService executor= Executors.newFixedThreadPool(20);
        List<MyTask> taskList = new ArrayList();
        for(int i=0;i<100;i++){
            taskList.add(new MyTask());
        }
        for(MyTask task:taskList){
            executor.submit(task);
        }
        executor.shutdown();
    }
}

/*class MyTask implements Runnable{


    @Override
    public void run() {
        System.out.println("Thread "+Thread.currentThread().getName()+" is executing the task");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}*/

class MyTask implements Callable {

    @Override
    public Object call() {
        System.out.println("Thread "+Thread.currentThread().getName()+" is executing the task");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
