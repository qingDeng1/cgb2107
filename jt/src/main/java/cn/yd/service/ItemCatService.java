package cn.yd.service;

import cn.yd.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {

    List<ItemCat> findAll(ItemCat itemCat);

    List<ItemCat> findItemCatList(Integer level);

    void updateStatusById(ItemCat itemCat);

    void saveItemCat(ItemCat itemCat);

    void deleteItemCat(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);
}
