package cn.yd.service;

import cn.yd.mapper.UserMapper;
import cn.yd.pojo.User;
import cn.yd.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    //根据u/p查询数据库
    @Override
    public String findUserByUP(User user) {
        //1.将密码加密
        byte[] bytes = user.getPassword().getBytes();
        String md5Pass = DigestUtils.md5DigestAsHex(bytes);
        System.out.println(md5Pass);
        user.setPassword(md5Pass);
        //2.根据用户名和密文查询数据库
        User userDB = userMapper.findUserByUP(user);
        //3.判断userDB是否有值
        if (userDB == null) {
            //查询没有数据.返回null
            return null;
        }
        //秘钥特点: 唯一性,迷惑性  UUID:几乎可以保证唯一性
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public PageResult getUserListByPage(PageResult pageResult) {
        //1、总数
        long total = userMapper.findTotal();
        //2、分页结果
        int size = pageResult.getPageSize();    //条数
        int start = (pageResult.getPageNum() - 1) * size;   //起始位置
        String query = pageResult.getQuery();   //查询条件

        //多值查询，封装成单值，map集合(一般)
        List<User> userList = userMapper.findUserListByPage(start, size, query);
        return pageResult.setTotal(total).setRows(userList);
    }

    @Transactional
    @Override
    public void updateStatusById(User user) {
        userMapper.updateStatusById(user);
    }

    /*
     * 1、实现业务封装
     * */

    @Transactional
    @Override
    public void addUser(User user) {
        String password = user.getPassword();
        String MD5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        //获取当前时间
        Date date = new Date();
        user.setPassword(MD5Pass).setStatus(true)
                .setCreated(date).setUpdated(date);
        userMapper.addUser(user);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        //设定当前时间
        Date date = new Date();
        user.setCreated(date);
        userMapper.updateUser(user);
    }
    /*
    * @Transactional 事务控制
    * 作用：
    *   1、默认条件下，只拦截运行时异常
    *   2、rollbackFor：指定异常回滚 rollbackFor=RuntimeException.class
    *   3、noRollbackFor：指定异常不会滚 noRollbackFor=RuntimeException.class
    * */
    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
