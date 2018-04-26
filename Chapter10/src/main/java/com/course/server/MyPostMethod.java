package com.course.server;


import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是我全部的post请求")
@RequestMapping("/v1")
public class MyPostMethod {

    //这个变量是用来装我们的cookies信息的
    private static Cookie cookie;

    //用户登录成功获取到cookies，然后在访问其他接口获取到列表
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies信息",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "password",required = true) String password){
        if (userName.equals("lily")&&password.equals("123456")){
            cookie=new Cookie("login","true");
            return "Congratuations to login!";
        }
        return "your userName or password is wrong!";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        User user;
        // /获取cookies
        Cookie[] cookies=request.getCookies();
        //验证cookies是否合法
        for (Cookie c:cookies){
            if (c.getName().equals("login")
                    && c.getValue().equals("true")
                    && u.getUserName().equals("lily")
                    && u.getPassword().equals("123456")){
                user=new User();
                user.setName("Lucy");
                user.setAge("18");
                user.setSex("woman");
                return user.toString();

            }
        }

        return "参数不合法";
    }
}
