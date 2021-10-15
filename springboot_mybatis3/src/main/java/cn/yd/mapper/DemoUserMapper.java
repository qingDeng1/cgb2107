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

    List<DemoUser> selectByUser(DemoUser user);

    int updateUserSet(DemoUser user);

    List<DemoUser> selectChoose(DemoUser demoUser);
}
