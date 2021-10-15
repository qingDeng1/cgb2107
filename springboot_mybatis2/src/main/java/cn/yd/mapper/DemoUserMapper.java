package cn.yd.mapper;

import cn.yd.pojo.DemoUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 1、根据面向接口开发的思想需要定义一个Mapper接口
 * 2、在接口中可以写接口方法，谁用谁去实现！！！
 * 3、Mybatis中的实现类以文件的形式存在
 * */
public interface DemoUserMapper {
    //1、查询所有的表数据
    List<DemoUser> findAll();

    Object findOne(int id);

    DemoUser findNmae(String name);

    List<DemoUser> findSex(String sex, int age);

    DemoUser findUserById(int id);

    int saveUser(DemoUser user);

    int updateUser(DemoUser user);

    int deleteUserById(int id);

    List<DemoUser> findUserListByAge(Map map);


    void deleteIds(int[] ids);

    void deleteList(List list);

    void deleteMap(HashMap map);

    List<DemoUser> select1(Map map);

    List<DemoUser> select2(Map map);

    List<DemoUser> select3(Map map);

    int updateNames(Map map);
}
