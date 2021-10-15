package cn.yd;

import cn.yd.mapper.UserMapper;
import cn.yd.pojo.User;
import cn.yd.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.List;

@SpringBootTest
class SpringbootSsmApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {

    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setId(null).setName("MybatisPlus")
                .setAge(10).setSex("男");
        userMapper.insert(user);
    }

    @Test
    public void selectById() {
        int id = 1;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    /*
     * *查询name="大乔", sex=”女”的用户
     *
     * 语法:
     *  1、根据对象中不为null的属性进行业务操作
     *  2、QueryWrapper条件构造器
     *  3、默认的关系连接符 and
     * */
    @Test
    public void selectByNs() {
        User user = new User();
        user.setName("大乔").setSex("女");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /*
     * 方式2：利用条件构造器，构件条件
     * 说明：
     *   1、eq =
     *   2、gt >
     *   3、lt <
     *   4、ge >=
     *   5、le <=
     *   6、ne <>
     * */
    @Test
    public void selectByNs2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "大乔")
                .eq("sex", "女");
        List list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    @Test
    public void selectByAS() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", "男")
                .gt("age", 18);
        List list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /*
     * *需求:查询name中包含”君"，性别=”女"
     * *sql: where name like "%看%"
     * *需求2:查询name中以”君"结尾的，性别=”女”like "%君”
     * likeLeft: %在左边
     * likeRight：%在右边
     * */
    @Test
    public void selectLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "君")
                .eq("sex", "女");
    }

    @Test
    public void selectIds() {
        Integer[] ids = {1, 3, 4, 5};
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids)
                .orderByDesc("age");
        List list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }

    /*
     * 只想获取第一列数据(主键),sex=”女"
     * 用法：selectObjs(queryWrapper);
     * 实际用途：做关联查询时可以使用
     * */
    @Test
    public void selectObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", "女");
        List<Object> list = userMapper.selectObjs(queryWrapper);
        System.out.println(list);
    }

    /*
     *需求:根据不为null的属性当做where动态sql实现
     *参数说明：
     *  1、boolean condition, true时，当前的条件才会成立
     */
    @Test
    public void selectByAs2() {
        Integer age = null;
        String sex = "女";
        //判断字符串是否为空
        //boolean flag = sex != null && "".equals(sex);
        boolean flag = StringUtils.hasLength(sex);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt(age != null, "age", age)
                .eq(flag, "sex", sex);
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
    }
}
