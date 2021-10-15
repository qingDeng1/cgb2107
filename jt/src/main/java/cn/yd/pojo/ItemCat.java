package cn.yd.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/3/26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("item_cat")
public class ItemCat extends BasePojo{
    @TableId(type = IdType.AUTO) //主键自增
    private Integer id;         //定义主键
    private Integer parentId;   //定义父级菜单
    private String name;        //分类名称
    private Boolean status;     //分类状态 0 停用 1 正常
    private Integer level;      //商品分类等级  1 2 3
    //exist:存在
    @TableField(exist = false)
    private List<ItemCat> children; //业务属性，需要告诉数据库
}
