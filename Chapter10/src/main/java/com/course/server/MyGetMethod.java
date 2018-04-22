package com.course.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MyGetMethod {

    /**
     * 这是一个会获取cookies信息的get请求
     * @param response
     * @return
     */

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    public String getCookies(HttpServletResponse response) {
        //HttpServletRequest 装请求信息的类
        //HttpServletResponse 装响应信息的类
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "Congratuations to get cookies!";
    }

    /**
     * 要求实现客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "you must come with cookies!";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "Congratuations to come in!";
            }
        }

        return "you must come with cookies";
    }

    /**
     * 这是一个需要携带参数才能访问的get请求
     * 第一种方式url:key=value&key=value
     * 我们模拟获取商品列表
     * http://localhost:8888/get/with/param?start=10&end=20
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> mylist=new HashMap<>();
        mylist.put("shose",400);
        mylist.put("coat",350);
        mylist.put("dompling",20);

        return mylist;
    }

    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
    @RequestMapping(value = "/get/with/param/{start}/{end}")
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> mylist=new HashMap<>();
        mylist.put("shose",400);
        mylist.put("coat",350);
        mylist.put("dompling",20);

        return mylist;
    }

}
