package cn.yd;

import cn.yd.mapper.DemoUserMapper;
import cn.yd.pojo.DemoUser;
import org.apache.catalina.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;


public class TestMybatis {
    @Test
    public void demo1() throws Exception {
        /*创建SqlSessionFactory*/
        String resource = "mybatis/mybatis-config.xml";

        //通过io流 加载指定的配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //动态生成SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*获取mapper接口,执行接口方法*/
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);

        //获取数据
        List<DemoUser> userList = demoUserMapper.findAll();
        System.out.println(userList);
    }

    @Test
    public void demo2() throws Exception{
        /*创建SqlSessionFactory*/
        String resource = "mybatis/mybatis-config.xml";

        //通过io流 加载指定的配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);

        //动态生成SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        /*从SqlSessionFactory中获取sqlSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*获取mapper接口,执行接口方法*/
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);

        //获取接口
        sqlSession.getMapper(DemoUserMapper.class);
        int id = 1;
        Object demoUser = demoUserMapper.findOne(id);
        System.out.println(demoUser);

    }
}
