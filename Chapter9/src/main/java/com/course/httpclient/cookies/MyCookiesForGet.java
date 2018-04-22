package com.course.httpclient.cookies;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
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
        this.store=new BasicCookieStore();//用来存储cookie信息的对象
        HttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();//获取cookies信息
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie:cookieList){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name = "+name+",cookie value = "+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String result;
        String uri=bundle.getString("test.get.with.cookies");
        String testUrl=this.url+uri;

        HttpGet get=new HttpGet(testUrl);
//        this.store=new BasicCookieStore();//用来存储cookie信息的对象

        HttpClient client = HttpClients.custom().setDefaultCookieStore(this.store).build();//获取cookies信息
        HttpResponse response = client.execute(get);

        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("statusCode = "+ statusCode);

        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

    }
}














/*        //4.1.2版本
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
        CookieStore store=client.getCookieStore();//获取cookies信息
*/
