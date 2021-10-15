package cn.yd.mapper;

import cn.yd.pojo.ItemCat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//mybatis-plus：需要继承BaseMapper<T>
//CURD操操作如果没有特殊需求可以省略
//如果没有sql需求，则xml映射文件可以简化
public interface ItemCatMapper extends BaseMapper<ItemCat> {

    @Select("SELECT * FROM `item_cat`")
    List<ItemCat> findAll(ItemCat itemCat);

    @Select("SELECT * FROM `item_cat` a WHERE parent_id = 0")
    List<ItemCat> findOne(Integer level);

    @Update("update `item_cat` set `status` = #{status} where id = #{id}")
    void updateStatusById(ItemCat itemCat);

    @Update("update `item_cat` set name = #{name} where id = #{id}")
    void updateItemCat(ItemCat itemCat);
}
