package com.practice;

import java.util.concurrent.*;

public class DivideAndConquerFindMinimum {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int array[] = {154,26,84,258,44,38,63,05,87,52,69,66};
        int min = findMinimum(array,0,array.length-1);
        System.out.println(min);
    }
///finding minimum based on divide and conquer algo like merge sort
    //now make use of multithreading to execute parrlaley to get value
    private static int findMinimum(int[] array, int startIndex, int endIndex) throws ExecutionException, InterruptedException {

        int midIndex,minvalue1,minvalue2;
        if(startIndex < endIndex){

            midIndex =(startIndex+endIndex)/2;

            ExecutorService service = Executors.newFixedThreadPool(4);

           Future<Integer> future1= service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"for min index : "+(startIndex)+ " for max index:  "+midIndex);
                    return findMinimum(array,startIndex,midIndex);
                }
            });

            Future<Integer> future2= service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"for min index : "+(midIndex+1)+ " for max index:  "+endIndex);
                    return findMinimum(array,midIndex+1,endIndex);
                }
            });
            minvalue1 = future1.get();
            minvalue2 = future2.get();
            service.shutdown();

            if(minvalue1>minvalue2)
                return minvalue2;
            return minvalue1;

        }else{
            return array[startIndex];
        }

    }


}
