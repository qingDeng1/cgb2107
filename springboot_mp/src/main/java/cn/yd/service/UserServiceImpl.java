package cn.yd.service;


import cn.yd.mapper.UserMapper;
import cn.yd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service    //将该类交给Spring容器管理
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper; //代理对象 JDK动态代理

    @Override
    public List<User> findAll() {
        //List<User> userList = userMapper.findAll();
        //return userList;
        return userMapper.findAll();
    }


}
