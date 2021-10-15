package cn.yd.mapper;

import cn.yd.pojo.DemoUser;
import org.apache.catalina.User;

import java.util.List;

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
}
