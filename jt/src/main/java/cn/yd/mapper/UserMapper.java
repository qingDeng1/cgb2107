package cn.yd.mapper;

import cn.yd.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    //    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where username=#{username} and password=#{password}")
    User findUserByUP(User user);

    @Select("SELECT count(1) total FROM `user`")
    long findTotal();

    //将多值封装为单值 一般使用对象/集合/Map
    List<User> findUserListByPage(@Param("start") int start, @Param("size") int size, @Param("query") String query);

    @Update("update `user` set `status` = #{status} where id = #{id}")
    void updateStatusById(User user);

//    @Insert("insert into user values(null,#{username},#{password},#{email},#{phone},#{status},#{created},#{updated})")
    void addUser(User user);

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);

    @Update("update user set phone=#{phone},email=#{email} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(Integer id);
}
