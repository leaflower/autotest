package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuit(){
        System.out.println("@BeforeSuite 运行了");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("@AfterSuite  运行了");
    }
}
