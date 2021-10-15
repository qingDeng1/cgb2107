package cn.yd;

import cn.yd.mapper.DemoUserMapper;
import cn.yd.pojo.DemoUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis2 {
    /*
    * mybatis的核心SqlSessionFacotry对象
    * */
    private SqlSessionFactory sqlSessionFactory;
    @BeforeEach //测试API中的注解 在执行@Test注解方法时，会提前执行
    public void init() throws Exception {
        //1、指定资源文件
        String resource = "mybatis/mybatis-config.xml";
        //2、通过IO流 加载指定配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //3、动态生成SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    @Test
    public void testMybatis01(){
        //4、获取SqlSession 类比 数据库 连接
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper接口
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        //获取数据
        List<DemoUser> userList = demoUserMapper.findAll();

        String name = "王昭君";
        DemoUser demoUser1 = demoUserMapper.findNmae(name);

        System.out.println(userList);
        System.out.println(demoUser1);
        //关闭链接
        sqlSession.close();

    }

//    @Test
//    public void testMybatis02(){
//        //4、获取SqlSession 类比 数据库 连接
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //获取Mapper接口
//        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
//        //获取数据
//        DemoUser d = new DemoUser();
//        d.setSex("女");
//        List<DemoUser> userList = demoUserMapper.findSex(d.getSex(), d.getAge());
//
//    }

}
