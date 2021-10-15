package cn.yd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class Dept implements Serializable {
    private Integer deptId;
    private String deptName;

    //一个部门下有多个用户.
    private List<Emp> lists;

}
