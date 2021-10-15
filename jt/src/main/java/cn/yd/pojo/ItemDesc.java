package cn.yd.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/7
 */
@Data
@Accessors(chain = true)
@TableName("item_desc")
public class ItemDesc extends BasePojo{
    //由于item.id=itemDesc.id 所以Id主键不用自增
    private Integer id;
    private String itemDesc;

}
