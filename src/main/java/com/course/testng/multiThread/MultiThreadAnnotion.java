package com.course.testng.multiThread;

import org.testng.annotations.Test;

/**
 * Created by chenli12 on 2018/4/20.
 */
public class MultiThreadAnnotion {
    @Test(invocationCount = 10,threadPoolSize = 3) //线程池threadPoolSize，3个线程
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id: %s%n",Thread.currentThread().getId());
    }
}
