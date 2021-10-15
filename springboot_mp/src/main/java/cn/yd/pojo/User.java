package cn.yd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("demo_user") //对象与表明映射
public class User implements Serializable {//表名demo_user
    /*摸一把
     * 规则：
     *  1、如果数据库中的字段与表中的属性名称一致，则可以省略不写
     *  2、如果对象与表明一致，则名称可以省略
     **/
    @TableId(type = IdType.AUTO) //主键自增
    private Integer id;
    private String name;
//    @TableField("age")
    private Integer age;
    private String sex;

}
