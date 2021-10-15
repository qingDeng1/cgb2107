package cn.yd.mapper;

import cn.yd.pojo.Dept;

import java.util.List;

public interface DeptMapper {

    List<Dept> selectFind(Dept dept);
}
