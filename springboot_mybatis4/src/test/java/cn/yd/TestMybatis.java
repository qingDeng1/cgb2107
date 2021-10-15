package cn.yd;

import cn.yd.mapper.DeptMapper;
import cn.yd.mapper.EmpMapper;
import cn.yd.pojo.DemoUser;
import cn.yd.pojo.Dept;
import cn.yd.pojo.Emp;
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

    @Test
    public void selectFind(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = new Dept();
        dept.setDeptId(100);
        List<Dept> list = mapper.selectFind(dept);
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void FindAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findAll();
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void FindSon(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findSon();
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void FindMore(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.findMore();
        System.out.println(list);
        sqlSession.close();
    }
}
