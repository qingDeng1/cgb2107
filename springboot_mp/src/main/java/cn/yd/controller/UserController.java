package cn.yd.controller;

import cn.yd.pojo.User;
import cn.yd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//编辑Controller
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //1、查询所有信息
    @RequestMapping("findAll")
    public Object findAll() {
        List<User> list = userService.findAll();
        return list;
    }
}
