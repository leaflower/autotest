package com.testng;

import org.testng.annotations.Test;

/**
 * 期望结果是异常，比如传入不合法的参数,程序抛出异常
 */
public class ExpectedException {
/*
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }
    */
    //测试结果会失败
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
