package com.practice;

import java.util.concurrent.*;

public class CallableAndFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Long> submit = executorService.submit(new factorial(10));
        System.out.println("hellooo1");
        System.out.println(submit.get());
        System.out.println("hellooo2");
        executorService.shutdown();
    }
}

class factorial implements Callable {

    private int num;

    factorial(int n){
    this.num=n;
    }

    @Override
    public Long call() throws Exception {
        long value=1;

        while(num>0){
            value = value*num;
            num--;
        }

        return value;
    }
}
