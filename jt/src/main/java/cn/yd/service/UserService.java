package cn.yd.service;

import cn.yd.pojo.User;
import cn.yd.vo.PageResult;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String findUserByUP(User user);

    PageResult getUserListByPage(PageResult pageResult);

    void updateStatusById(User user);

    void addUser(User user);

    User selectUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
