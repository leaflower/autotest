package com.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu1(){
        System.out.println("GroupsOnClass22中的stu111运行");
    }
    public void stu2(){
        System.out.println("GroupsOnClass22中的stu222运行");
    }
}
