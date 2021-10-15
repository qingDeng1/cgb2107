package cn.yd.mapper;

import cn.yd.pojo.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item> {
    @Select("SELECT * FROM `item`")
    List<Item> findAll(Item item);

    @Select("SELECT count(1) total FROM `item`")
    long findTotal();

    List<Item> findItemListByPage(@Param("start") int start, @Param("size") int size, @Param("query") String query);;

    @Update("update `item` set status = #{status} where id = #{id}")
    void updateItemStatus(Item item);

}
