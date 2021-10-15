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

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    //3.1、根据name和age查询用户信息
    @Override
    public List<User> findMore(String name, Integer age) {
        HashMap map = new HashMap();
        map.put("name", name);
        map.put("age", age);
        return userMapper.findMore(map);
    }

    //3.2查询名称以"xxx"结尾的数据.   URL: http://localhost:8090/findUserLike?name=“xx”
    @Override
    public List<User> findDim(String name) {
        String names = "%"+name;
        return userMapper.findDim(names);
    }


    //3.3查询id为 1,3,5,6,7的数据    URL: http://localhost:8090/findUserByIds?id=1,3,5,6,7
    @Override
    public List<User> findIds(String id) {
        String[] idStr = id.split(",");
        Integer[] intArray = new Integer[idStr.length];
        for (int i=0;i<idStr.length;i++){
            intArray[i] = Integer.parseInt(idStr[i]);
        }
        return userMapper.findIds(intArray);
    }


    /*3.4根据对象中不为null的元素查询数据 动态Sql查询
        URL: http://localhost:8090/findUser?id=xx&name=“xx”&age=xx&sex=xx
        URL: http://localhost:8090/findUser?age=xx&sex=xx
        URL: http://localhost:8090/findUser?sex=xx
    */

    @Override
    public List<User> findNull(Integer id, String name, Integer age, String sex) {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        map.put("sex", sex);
        return userMapper.findNull(map);
    }
/*3.5更新操作 利用restFul的结构, 根据Id修改数据,修改name/age
        URL:http://localhost:8090/user/貂蝉/18/227
        解析: URL:http://localhost:8090/user/{name}/{age}/{id}*/

    @Override
    public void updateMore(Integer id, String name, Integer age, String sex) {
        HashMap map = new HashMap();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        map.put("sex", sex);
        userMapper.updateMore(map);
    }
}
