package com.practice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        //2 thread 2 run sepratly for demo of thread local, how work.
       // thread local create new object of its thread local instance for variable, if new thread come.
        // internally it will track by jvm for new thread and creation of new thread local  variable speific to that thread.
        Thread t1 = new Thread(new printable());
        //so these prinatable calls call fomratter class
        Thread t2= new Thread(new printable());
        t1.start();
        t2.start();
    }
}

class DateFormatPerThread{
    private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        //this intialvalue method of ThreadLocal will be called when we call get() on threadlocal object internally initailvalue() will be get called
        //so override super ka initialValue to return specific type and data.
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("dd/MM/yyyy");
        }
     };
    //thhis .get method call initailValue() of thread local and return dateformat obj. every thread will ask and check and create new thread
    ///local object specific will new thread and associate with them.
        public static DateFormat getDateFomat(){
            return dateFormat.get();
        }
}

class printable implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<=2;i++){
            System.out.println("Thread: "+Thread.currentThread().getName()+" formatted date: "+DateFormatPerThread.getDateFomat().format(new Date()));
        }
    }
}




