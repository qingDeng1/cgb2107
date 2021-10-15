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
import java.util.List;

public class TestMybatis {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        /*创建SqlSessionFactory*/
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /*动态Sql*/
    @Test
    public void testSelect(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        DemoUser user = new DemoUser();
        user.setName("王昭君");
        List<DemoUser> userList = userMapper.selectByUser(user);
        System.out.println(userList);
        sqlSession.close();
    }

    /*动态Sql 更像操作
     * 修改ID=236的用户数据 名称改为张三,age=18,sex=女
     * */
    @Test
    public void testUpdate(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper userMapper = sqlSession.getMapper(DemoUserMapper.class);
        DemoUser user = new DemoUser(236,"张三",18,"女");
        int rows = userMapper.updateUserSet(user);
        if(rows > 0){
            sqlSession.commit();
        }
        sqlSession.close();
    }

    @Test
    public void selectChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DemoUserMapper demoUserMapper = sqlSession.getMapper(DemoUserMapper.class);
        DemoUser demoUser = new DemoUser(null,null,null,"男");
        List<DemoUser> list = demoUserMapper.selectChoose(demoUser);
        System.out.println(list);
        sqlSession.close();
    }

}
