package cn.yd.service;

import cn.yd.mapper.ItemCatMapper;
import cn.yd.mapper.ItemDescMapper;
import cn.yd.mapper.ItemMapper;
import cn.yd.pojo.Item;
import cn.yd.pojo.ItemDesc;
import cn.yd.vo.ItemVO;
import cn.yd.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public List<Item> findAll(Item item) {
        return itemMapper.findAll(item);
    }

    /*SQL实现分页*/
//    @Override
//    public PageResult getItemList(PageResult pageResult) {
//        //1、总数
//        long total = itemMapper.findTotal();
//        //2、分页结果
//        int size = pageResult.getPageSize();    //条数
//        int start = (pageResult.getPageNum() - 1) * size;   //起始位置
//        String query = pageResult.getQuery();   //查询条件
//
//        //多值查询，封装成单值，map集合(一般)
//        List<Item> itemList = itemMapper.findItemListByPage(start, size, query);
//        return pageResult.setTotal(total).setRows(itemList);
//    }

    /*MP实现分页
    * 语法：selectPage语法说明
    *       1、page:MP内部指定的分页对象
    *       2、queryWrapper:条件构造器
    *       SQL: where title like "%xx%"
    *
    * */
    @Override
    public PageResult getItemList(PageResult pageResult) {
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(flag,"title", pageResult.getQuery());
        //编辑MP的分页对象四个属性有用(页数页数/条数/总数/记录) 传递=页数/条数
        IPage<Item> page = new Page<>(pageResult.getPageNum(),pageResult.getPageSize());
        page = itemMapper.selectPage(page, queryWrapper);
        //1、总数
        long total = page.getTotal();
        //2、获取记录数
        List<Item> rows = page.getRecords();
        return pageResult.setTotal(total).setRows(rows);
    }

    @Override
    public void updateItemStatus(Item item) {
//        itemMapper.updateItemStatus(item);
        itemMapper.updateById(item);
    }

    @Override
    public void deleteItemById(Item item) {
        itemMapper.deleteById(item);
    }

    @Override
    public void saveItem(ItemVO itemVo) {
        Item item = itemVo.getItem();
        item.setStatus(true);
        itemMapper.insert(item);
        //实现itemDesc对象入库,动态返回ID
        //MP原则，动态回显数据
        ItemDesc itemDesc = itemVo.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
    }
}
