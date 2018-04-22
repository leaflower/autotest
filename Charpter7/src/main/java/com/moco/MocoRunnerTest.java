package com.moco;

import com.github.dreamhead.moco.Runner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class MocoRunnerTest {
    private Runner runner;

    @BeforeTest
    public void setup() {
//        HttpServer server = httpserver(12306);
//        server.response("foo");
//        runner = runner(server);
//        runner.start();

    }

    @AfterTest
    public void tearDown() {
        runner.stop();
    }

    @Test
    public void should_response_as_expected() throws IOException {
//        Content content = Request.Get("http://localhost:12306").execute().returnContent();
//
//        assert(content.toString()).equals(is("foo"));


    }

}