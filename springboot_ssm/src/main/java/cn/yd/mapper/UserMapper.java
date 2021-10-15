package cn.yd.mapper;


import cn.yd.pojo.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component(value = "userMapper")
public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    //3.1、根据name和age查询用户信息
    List<User> findMore(HashMap map);

    //3.2查询名称以"xxx"结尾的数据.   URL: http://localhost:8090/findUserLike?name=“xx”
    List<User> findDim(String names);

    //3.3查询id为 1,3,5,6,7的数据    URL: http://localhost:8090/findUserByIds?id=1,3,5,6,7
    List<User> findIds(Integer[] intArray);

    /*3.4根据对象中不为null的元素查询数据 动态Sql查询
        URL: http://localhost:8090/findUser?id=xx&name=“xx”&age=xx&sex=xx
        URL: http://localhost:8090/findUser?age=xx&sex=xx
        URL: http://localhost:8090/findUser?sex=xx
    */
    List<User> findNull(HashMap map);

    /*3.5更新操作 利用restFul的结构, 根据Id修改数据,修改name/age
        URL:http://localhost:8090/user/貂蝉/18/227
        解析: URL:http://localhost:8090/user/{name}/{age}/{id}*/
    void updateMore(HashMap map);
}
