package cn.yd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

@Data //获取get/set方法
@Accessors(chain = true) //开启链式加载，重写setId等方法
@AllArgsConstructor //全参构造
@NoArgsConstructor  //午餐构造
//@Alias("user")  //起别名
public class DemoUser {

    private Integer id;
    private String name;
    private Integer age;
    private String sex;


}
