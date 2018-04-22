package com.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Created by chenli12 on 2018/4/20.
 */
public class GroupsOnMethod {

    @Test(groups = "client",enabled = true)
    public void test1(){
        System.out.println("这是客户端的测试方法1111");
    }
    @Test(groups = "server")
    public void test2(){
        System.out.println("这是客户端的测试方法2222");
    }
    @Test(groups = "server",enabled = true)
    public void test3(){
        System.out.println("这是服务端的测试方法33333");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("这是服务端的测试方法44444");
    }

    @BeforeGroups(groups = "client")
    public void beforeGroupsOnClient(){
        System.out.println("客户端组test要运行了");
    }
    @AfterGroups(groups = "client")
    public void afterGroupsOnClient(){
        System.out.println("客户端组test运行完了");
    }

    @BeforeGroups(groups = "server")
    public void beforeGroupsOnServer(){
        System.out.println("服务端组test要运行了");
    }
    @AfterGroups(groups = "server")
    public void afterGroupsOnServer(){
        System.out.println("服务端组test运行完了");
    }
}
