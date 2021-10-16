package cn.yd.controller;

import cn.yd.pojo.Item;
import cn.yd.service.ItemService;
import cn.yd.vo.ItemVO;
import cn.yd.vo.PageResult;
import cn.yd.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/findAll")
    public List<Item> findAll(Item item) {
        List<Item> list = itemService.findAll(item);
        return list;
    }

    /*
     * 需求： 商品列表展现
     * 请求路径: /item/getItemList?query=&pageNum=1&pageSize=10
     * 请求类型: get
     * 使用pageResult对象接收
     * */
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult) {
        pageResult = itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }

    /*
     * 需求：商品状态修改
     * 请求路径: /item/updateItemStatus
     * 请求类型: put
     * 请求参数: 使用对象接收
     * */
    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item) {
        itemService.updateItemStatus(item);
        return SysResult.success();
    }

    /*
     * 需求：商品数据删除
     * 请求路径: /item/deleteItemById
     * 请求类型: delete
     * 请求参数: id
     * */
    @DeleteMapping("/deleteItemById")
    public SysResult deleteItemById(Item item) {
        itemService.deleteItemById(item);
        return SysResult.success();
    }

    /*
     * 需求：商品数据修改
     * 请求路径: /item/updateItemById
     * 请求类型: put
     * 请求参数: 使用对象接收
     * 请求参数: 表单数据 Item对象
     * 返回值: SysResult对象
     * */
    @PutMapping("/updateItem")
    public SysResult updateItem(@RequestBody Item item) {
        itemService.updateItem(item);
        return SysResult.success();
    }


    /*
    * 实现商品的新增
    *
    * */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVo) {
        itemService.saveItem(itemVo);
        return SysResult.success();
    }
}
