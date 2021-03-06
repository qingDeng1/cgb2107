package cn.yd.vo;

import cn.yd.pojo.Item;
import cn.yd.pojo.ItemDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO {  //该对象封装商品所有的参数信息
    private Item item;
    private ItemDesc itemDesc;
}
