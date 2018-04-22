package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {

    @Test
    public void test1(){
        System.out.printf("Thread Id: %s%n",Thread.currentThread().getId());
        System.out.println();
    }

    @Test
    public void test2(){
        System.out.printf("Thread Id: %s%n",Thread.currentThread().getId());
        System.out.println();
    }

    @Test
    public void test3(){
        System.out.printf("Thread Id: %s%n",Thread.currentThread().getId());
        System.out.println();
    }
}
