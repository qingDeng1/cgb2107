package cn.yd;

import cn.yd.mapper.DemoUserMapper;
import cn.yd.pojo.DemoUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    /**
     * 查询age>=18 age<=100岁的用户
     */
    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        /*创建SqlSessionFactory*/
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testSaveUser() {
        DemoUser user = new DemoUser(null, "佛媛", 18, "女");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        int rows = userMapper.saveUser(user);
        if (rows > 0) {
            System.out.println("添加成功！！！");
            sqlSession.commit();
        }
        System.out.println(rows);
        sqlSession.close();
    }

    @Test
    public void testUpdateUser() {
        DemoUser user = new DemoUser(234, "中秋节BBBB", 2021, "女");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        int rows = userMapper.updateUser(user);
        if (rows > 0) {
            System.out.println("事物提交");
            sqlSession.commit();
        }
        System.out.println(rows);
        sqlSession.close();
    }

    @Test
    public void testDeleteUserById() {
        int id = 237;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        int rows = userMapper.deleteUserById(id);
        if (rows > 0) {
            System.out.println("事物提交");
            sqlSession.commit();
        }
        System.out.println(rows);
        sqlSession.close();
    }


    @Test
    public void testUserById() {
        int id = 1;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        DemoUser user = userMapper.findUserById(id);
        System.out.println(user);
        sqlSession.close();
    }


    @Test
    public void testSelectUserListByAge() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map map = new HashMap();
        map.put("minAge", 18);
        map.put("maxAge", 100);
        List<DemoUser> userList = userMapper.findUserListByAge(map);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testDeleteIds() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        int[] ids = {232, 233, 234};
        demoUserMapper.deleteIds(ids);
        System.out.println("删除成功！！！");
        sqlSession.close();
    }

    @Test
    public void testDeleteList() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List list = new ArrayList();
        list.add(235);
        list.add(236);
        list.add(237);
//        HashMap map = new HashMap();
//        map.put("ids", list);
        demoUserMapper.deleteList(list);
        System.out.println("删除成功！！！");
        sqlSession.close();
    }

    @Test
    public void testDeleteMap() {
        //自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        List list = new ArrayList();
        list.add(232);
        list.add(233);
        list.add(234);
        HashMap map = new HashMap();
        map.put("ids", list);
        demoUserMapper.deleteMap(map);
        System.out.println("删除操作成功!!!");
    }

    //2.1 查询age<100岁的女性用户.
    @Test
    public void testSelect1() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map map = new HashMap();
        map.put("age", 100);
        map.put("sex", "女");
        List<DemoUser> userList = demoUserMapper.select1(map);
        System.out.println(userList);
        sqlSession.close();
    }

    //2.2 查询name中包含"精"的数据.并且按照年龄降序排列
    @Test
    public void testSelect2() {
        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取mapper接口,执行接口方法*/
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map map = new HashMap();
        map.put("name", "%精%");
        List<DemoUser> userList = demoUserMapper.select2(map);
        System.out.println(userList);
        sqlSession.close();
    }

    //2.3 查询age位于100-300的数据
    @Test
    public void testSelect3() {
        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取mapper接口,执行接口方法*/
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        Map map = new HashMap();
        map.put("age1", 100);
        map.put("age2", 300);
        List<DemoUser> userList = demoUserMapper.select3(map);
        System.out.println(userList);
        sqlSession.close();
    }

    //2.4 将name为小乔/大乔/王昭君的年龄改为18岁,性别女
    @Test
    public void testUpdate() {
        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        /*获取mapper接口,执行接口方法*/
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        String[] array = {"小乔", "大乔", "王昭君"};
        Map map = new HashMap();
        map.put("name", array);
        map.put("age", 18);
        map.put("sex", "女");
        demoUserMapper.updateNames(map);
        System.out.println("修改成功");
        sqlSession.close();
    }
}
