package cn.yd.mapper;


import cn.yd.pojo.Emp;

import java.util.List;

public interface EmpMapper {

    List<Emp> findAll();

    List<Emp> findSon();

    List<Emp> findMore();
}
