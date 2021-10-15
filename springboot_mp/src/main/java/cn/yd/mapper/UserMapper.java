package cn.yd.mapper;


import cn.yd.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
