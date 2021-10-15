package cn.yd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 说明：
 *  1、将该类交给Spring容器管理
 *  2、SpringMVC负责调用该对象接受用户的请求
 *  3、将业务处理之后，为页面返回JSON数据
 *  @
 * */
@RestController
@PropertySource(value="classpath:/mysql.properties",encoding = "UTF-8")
@RequestMapping("jdbc")
public class JDBCController {
    //${key} Spring提供的spring表达式，简称为：spel表达式
    //语法：从spring容器内获取key，动态为属性赋值
    @Value("${mysql.username}")
    String username; // "root";
    @Value("${mysql.password}")
    String password; // "123456";

    @RequestMapping("getMsg")
    public String getMsg() {
        return "你好数据库:" + username + password;
    }

    @Value("${mysql.username1}")
    String username1;
    @Value("${mysql.password1}")
    String password1;

    @RequestMapping("getMsg1")
    public String getMsg1() {
        return username1 + password1;
    }

}
