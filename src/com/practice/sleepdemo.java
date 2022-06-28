package com.practice;

import java.util.Scanner;

//slepp() used to sleep the thread it means it will be go to sleep from running state and then again to runnable state once sleep time completed
// and then it will picked by scheduler to execute rest of task.till that time that thread hold thae resources if any having and its keeping
//away from pool availability.
//it will useful when it is looking for some task to complete asunchronously and wait for other service call result etc.
public class sleepdemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("tring... tring.... tring....");
        System.out.println("1.hit to stop watch");
        System.out.println("2. hot snooze pause 10 sec.");
        System.out.println("select 1 option");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        if(i==1){
            System.out.println("alarm stopped");
        }else{
            System.out.println("ringing suspended for 10 sec");
            Thread.sleep(10000);//this will sleep the thread for 10 sec and go to runnable state and
            System.out.println("tring... tring.... tring....");
        }
    }
}
