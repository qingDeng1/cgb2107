package cn.yd.service;


import cn.yd.pojo.User;

import java.util.List;

// 编辑Service接口和实现类
public interface UserService {
    //查询user表中的所有的数据
    List<User> findAll();

    User findById(Integer id);

    //3.1、要求根据name和age查询用户信息
    List<User> findMore(String name,Integer age);

    //3.2查询名称以"xxx"结尾的数据.   URL: http://localhost:8090/findUserLike?name=“xx”
    List<User> findDim(String name);

    //3.3查询id为 1,3,5,6,7的数据    URL: http://localhost:8090/findUserByIds?id=1,3,5,6,7
    List<User> findIds(String id);

    /*3.4根据对象中不为null的元素查询数据 动态Sql查询
        URL: http://localhost:8090/findUser?id=xx&name=“xx”&age=xx&sex=xx
        URL: http://localhost:8090/findUser?age=xx&sex=xx
        URL: http://localhost:8090/findUser?sex=xx
    */
    List<User> findNull(Integer id,String name,Integer age,String sex);

    /*3.5更新操作 利用restFul的结构, 根据Id修改数据,修改name/age
        URL:http://localhost:8090/user/貂蝉/18/227
        解析: URL:http://localhost:8090/user/{name}/{age}/{id}*/
     void updateMore(Integer id, String name, Integer age, String sex);

}
