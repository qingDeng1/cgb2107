package cn.yd.controller;

import cn.yd.pojo.ItemCat;
import cn.yd.pojo.Rights;
import cn.yd.service.RightsService;
import cn.yd.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {
    @Autowired
    private RightsService rightsService;

    /*
    * get('/rights/getRightsList')
    * 业务说明：完成菜单列表查询1-2级
    * URL：/rights/getRightsList
    * 参数：没有参数
    * 返回值：SysResult(List)
    * */
    @GetMapping("/getRightsList")
    public SysResult getRightsList() {
        List<Rights> list = rightsService.getRightsList();
        return SysResult.success(list);
    }

    @GetMapping("/findRightsList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level) {
        List<Rights> list = rightsService.findRightsList(level);
        return SysResult.success(list);
    }



}
