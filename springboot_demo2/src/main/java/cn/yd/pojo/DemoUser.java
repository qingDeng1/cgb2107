package cn.yd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * 实体对象要求：
 *   1、类名一般与字段关联
 *   2、属性名称一般与字段关联
 *   3、pojo中的属性类型必须为引用类型（包装类型）
 *   4、实体对象必须得有get/set方法
 *   5、一般实体对象需要实现序列化接口(规则)
 *       原因：我们的数据可能跨平台（跨服务器）传输
 * */

@Data  // 动态生成get/set/toString/equals等方法
@Accessors(chain = true) //开启链式加载，重写set方法
@NoArgsConstructor    //无参构造
@AllArgsConstructor  //全参构造
public class DemoUser implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

//    public DemoUser setId(Integer id){
//        this.id = id;
//        return this;
//    }

    public void add() {
        DemoUser user = new DemoUser();
        user.setId(100)
                .setName("张三")
                .setAge(18)
                .setSex("男");
    }

}
