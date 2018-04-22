package com.course.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private BasicCookieStore store;//用来存储cookie信息的对象


    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void testGetCookies() throws IOException {
        //从配置文件中拼接url
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        //4.5.4版本
        HttpGet get = new HttpGet(testUrl);
        this.store = new BasicCookieStore();//用来存储cookie信息的对象
        HttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();//获取cookies信息
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name + ",cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri=bundle.getString("test.post.with.cookies");
        String testUrl=this.url+uri;

        //声明一个client对象，并设置cookies信息
        HttpClient client=HttpClients.custom().setDefaultCookieStore(store).build();

        //声明一个post方法
        HttpPost post=new HttpPost(testUrl);

        //添加参数
        JSONObject param=new JSONObject();
        param.put("name","lily");
        param.put("age","18");

        //设置请求信息，设置header
        post.setHeader("accept","application/xml;application/json;charset=utf-8");
        post.setHeader("content-type","application/json");

        //将参数信息添加到方法中
        StringEntity entity=new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);

        //执行post方法
        HttpResponse response= client.execute(post);

        //声明一个对象来进行相应结果的存储，获取结果
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        //处理结果，就是判断返回结果是否符合预期
        //将返回的相应结果字符串转化为json对象
        JSONObject resultJson=new JSONObject(result);
        //具体的判断返回结果的值

        String success=(String) resultJson.get("lily");
        Assert.assertEquals("success",success);

        String status=(String) resultJson.get("status");
        Assert.assertEquals("1",status);

    }
}
