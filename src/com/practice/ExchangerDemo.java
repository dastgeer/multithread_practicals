package com.practice;

import java.util.concurrent.Exchanger;
//
//---> exchanger class the represent the meeting point for 2 threads where they can exchange the object. this is like synchronous queue.
//        synchronus queue is blocking queue that will have  size one.that will hold only single element. producer send single element and consumer
//        recieve single lement only at a time.
//        but excahnager is almost like synchronos queue but not it is.because excahnge will share the object between 2 thread.
//        exchanges happens in both direction like thread 1 get thread 2 object and thread 2 object get thread 1 object.
//        exchanges will do both producer and consumer opeartions.
//        single thread wont able to do exchange will wait for another thread to start exchnager operation. till that time first come thread will be bloked
//        until some other thread come to exchange.
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        Thread t1= new Thread(new Exchange(exchanger,"Apple"));
        Thread t2 = new Thread(new Exchange(exchanger,"Banana"));
        Thread t3 = new Thread(new Exchange(exchanger,"orange"));
        Thread t4 = new Thread(new Exchange(exchanger,"milk"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        //this excahnger will work propely on even numbr of thread and create pair of thread for excahnge and then clear data and then it will
        //pick up the pair of thread to do exchange if even then one pair executed and other single will be wiaitinfg for other thread for exchange operation


    }
}
class Exchange implements Runnable{
    private Exchanger ex;
    private String item;

    Exchange(Exchanger exc,String itme){
        this.ex= exc;
        this.item = itme;
    }

    @Override
    public void run() {
        try {
            //pervious item which is currntly associate beofre excahnger
            String previousObject= item;
            //now it is overriding the exisitng current thread value with value of other thread who have pass value to exchanges
            item = (String) ex.exchange(item);
            System.out.println(Thread.currentThread().getName()+"exchanged :"+previousObject+" with :"+item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
