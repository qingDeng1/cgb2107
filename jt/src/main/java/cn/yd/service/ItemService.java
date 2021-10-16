package cn.yd.service;

import cn.yd.pojo.Item;
import cn.yd.vo.ItemVO;
import cn.yd.vo.PageResult;

import java.util.List;

public interface ItemService {

    List<Item> findAll(Item item);

    PageResult getItemList(PageResult pageResult);

    void updateItemStatus(Item item);

    void deleteItemById(Item item);

    void saveItem(ItemVO itemVo);

    void updateItem(Item item);
}
