package com.practice;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //intialized the thread pool of 4 thread

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        //it htis we will use functionainterface type compltable fture methos  with method chanining
        //this is the resolved state because we have provide inital value and on top of it chains are running.
        CompletableFuture<Void> compFutuyre =CompletableFuture.supplyAsync(()->1,executorService). //this act as supplier interface wich will return output only
                thenApplyAsync(e-> e+1,executorService)//this will act as function interface acept and return output
                .thenApplyAsync(e-> addone(e),executorService)// then it will also add and pass to the next chain
                .thenApplyAsync(e->e,executorService)//this intentionally to demo exception ocur at runtime and flow go to exception pipline
                .thenApplyAsync(e->addone(e),executorService)// this pipeline will be skip because abobe pipleine throw exception and move directly to exception pipeline
                .exceptionally(ex-> handleOccuredExecption(ex))//it will handle the exception and pass result flow if returning
                                                    //retuning any thing to build chain other wise it will break chain .
                                        //ther si 2 pipleine channel run in completeable future 1. data  2. error if recovereved then move to data f exception
                                        //then moved to error channel context get switches. if any chain throw exception and if there is any excpetionally()
        //then it will direct jump to that method and skip between pipline chain. that is the beauty and we have handle it properly.
            .thenAcceptAsync(e-> System.out.println(e),executorService) //this will habdle the consumer interface it wont return any thingmean pipleine end
            .thenRunAsync(()-> executorService.shutdown());// this method is take runnable it will run asychonous and return completeablefuture<void>
                //we have done shutdown of executor service and pool in that only.



        /////////////////////build pipline first then last pas value and pipline or chain of method will execute and give result.////////////////////////////
        //complteable future have 3 state : 1. pending 2. reject 3 . resolve

        //this is pending state where we didnt provide value it willbe wait for value to be execute up on till taht it will wait and pending state
        // it is kind of placeholder we  have design as template.
        //below is without execuotr if we dont provide executor thread pool then it will take forkjoin common pool
        CompletableFuture<Integer> compFuture= new CompletableFuture<>();
        compFuture.thenApplyAsync(e-> e*10)
                .thenApplyAsync(e-> e+10)
                .thenAcceptAsync(e-> System.out.println(e));
        compFuture.complete(10); /// now it will go from pending to resolve state when we call complete() with value
        //compFuture.completeOntimeOut(1, 1,TIMEUNIT.SECOND); this will tell if not completed in 1 second then resolve with value provided on complete. if only if in pending state.

    ////////////////////thencombine()/////////////////
        CompletableFuture<Integer> cf1=  CompletableFuture.supplyAsync(()->5)
         .thenApply(e -> e * 10)
                .thenApply(e -> e + 10);

        cf1.complete(1);//this will work when we hve not supplied any value it will directly returnthis value to make it complete

        CompletableFuture<Integer> cf2= CompletableFuture.supplyAsync(()->5)
                                .thenApply(e -> e * 5)
                                 .thenApply(e -> e + 5);

        cf2.complete(1);//this will work when we hve not supplied any value it will directly returnthis value to make it complete

        //this wil work in this way , when cf1 get resolve state then it will call combine() then wait for cf2 to be resolve onece both resolved
        //then both reults cf1 & cf2  willbe perform for further opeation in 2 nd arguent as bifunction and then foward result to next piplene stage.
        CompletableFuture<Integer> integerCompletableFuture = cf2.thenCombine(cf1, (t1, t2) -> t1+t2);
        System.out.println(integerCompletableFuture.get());

///thencompose () if any of method or function return ing complatablefuture only rather than using then apply and get completefuture of specific type
        //then use thencompose() internally it will flatten in result of completeable future and then summarized result.
    //allof()
        //anyof()
        //handle()

    }

    private static int addone(int e) {
        return e+1;
    }
    private static int handleOccuredExecption(Throwable ex){
        System.out.println("execption occured..."+ex);
        return 0;
    }
}
