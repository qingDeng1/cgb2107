package cn.yd.controller;

import cn.yd.pojo.ItemCat;
import cn.yd.service.ItemCatService;
import cn.yd.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/findAll")
    public List<ItemCat> findAll(ItemCat itemCat) {
        List<ItemCat> list = itemCatService.findAll(itemCat);
        return list;
    }

    /*
     * 需求：查询3级商品分类信息
     * 请求路径: /itemCat/findItemCatList/{level}
     * 请求类型: get
     * 请求参数: level
     * 返回值：SysResult(list)
     * */
    @GetMapping("/findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level) {
        List<ItemCat> list = itemCatService.findItemCatList(level);
        return SysResult.success(list);
    }

    /*
     * 需求：修改商品分类状态
     * 请求路径: /itemCat/status/{id}/{status}
     * 请求类型: put
     * 请求参数: id，status
     * 返回值: SysResult对象
     */
    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatusById(ItemCat itemCat){
        itemCatService.updateStatusById(itemCat);
        return SysResult.success();
    }

    /*
    * 需求：商品分类新增
    * 请求路径: /itemCat/saveItemCat
    * 请求类型: post
    * 请求参数: 表单数据,json
    * 返回值: SysResult对象
    * */
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat) {
        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    /*
    * 需求：商品分类修改
    * 请求路径: /itemCat/updateItemCat
    * 请求类型: put
    * 请求参数: 表单数据 ItemCat对象
    * 返回值: SysResult对象
    * */
    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat) {
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }


    /*
     * 需求： 商品分类删除
     * 请求路径: /itemCat/deleteItemCat?id=xx&level=xx
     * 请求类型: delete(和get类型一样)
     * 业务描述: 当删除节点为父级时,应该删除自身和所有的子节点
     * 请求参数:id/level
     * 返回值结果 SysResult对象
     * */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat) {
        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }
}
