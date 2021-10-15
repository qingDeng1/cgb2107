package cn.yd.service;


import cn.yd.pojo.User;

import java.util.List;

// 编辑Service接口和实现类
public interface UserService {
    //查询user表中的所有的数据
    List<User> findAll();

}
