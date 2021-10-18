package cn.yd.service;

import cn.yd.mapper.RightsMapper;
import cn.yd.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public List<Rights> getRightsList() {
//        return rightsMapper.getRightsList();

        //利用左连接来实现
        return rightsMapper.getRightsList2();
    }

    public Map<Integer, List<Rights>> getMap() {
        Map<Integer, List<Rights>> map = new HashMap<>();
        //null,查询数据库中所有的数据
        List<Rights> list = rightsMapper.selectList(null);
        //1、遍历数据
        for (Rights rights : list) {
            int parentId = rights.getParentId();
            //containsKey()判断key是否存在
            if (map.containsKey(parentId)) {
                //key存在，把数据追加到list集合中
                map.get(parentId).add(rights);
            } else {
                //key不存在，定义list集合，将自己作为第一个元素追加
                List<Rights> childrenList = new ArrayList<>();
                childrenList.add(rights);
                //将数据保存到map集合中
                map.put(parentId, childrenList);
            }
        }
        return map;
    }

    //该方法获取1-2级数据信息
    public List<Rights> getTwoList(Map<Integer, List<Rights>> map) {
        //1、先查询一级菜单数据
        List<Rights> oneList = map.get(0);
        //2、遍历每个一级菜单去封装二级数据
        for (Rights oneItemCat : oneList) {
            //查询二级数据
            int parentId = oneItemCat.getId();
            //查询二级的数据
            List<Rights> twoList = map.get(parentId);
            oneItemCat.setChildren(twoList);
        }
        return oneList;
    }

    /*
     * 实现思路：
     *       1、获取二级分类列表信息
     *       2、遍历一级菜单，获取二级数据
     *       3、根据二级菜单查询三级数据 防止二级数据为null的现象
     *       4、将三级数据封装到二级
     * */
    //该方法获取1-3级数据信息
    public List<Rights> getThreeList(Map<Integer, List<Rights>> map) {
//        //1、先查询一级和二级菜单数据
//        List<ItemCat> oneList = map.get(0);
//        //2、遍历每个一级菜单去封装二级数据
//        for (ItemCat oneItemCat : oneList) {
//            int parentId = oneItemCat.getId();
//            //查询二级的数据
//            List<ItemCat> twoList = map.get(parentId);
//            oneItemCat.setChildren(twoList);
//            //遍历封装三级数据
//            for (ItemCat twoItemCat : twoList) {
//                //查询三级的数据
//                List<ItemCat> threeList = map.get(twoItemCat.getId());
//                twoItemCat.setChildren(threeList);
//            }
//        }

        //1、获取二级分类列表信息（包含了二级children）
        List<Rights> oneList = getTwoList(map);
        //2、遍历一级菜单，获取二级数据
        for (Rights oneItemCat : oneList) {
            List<Rights> twoList = map.get(oneItemCat.getId());
            //3、根据二级菜单查询三级数据 防止二级数据为null的现象
            if (twoList == null || twoList.size() == 0) {
                /*
                 * twoList == null || twoList.size() == 0 顺序不能乱写，null在前
                 * 如果twoList.size()在前，当twoList为null时，null.size()又会报错
                 * */
                //跳过本次循环，进入下一次循环
                continue;
            }
            for (Rights twoItemCat : twoList) {
                //查询三级的数据
                List<Rights> threeList = map.get(twoItemCat.getId());
                twoItemCat.setChildren(threeList);
            }
        }
        return oneList;
    }


    /*
     * 如果使用常规方法 耗时：900毫秒
     * 经过优化：
     * */
    @Transactional
    @Override
    public List<Rights> findRightsList(Integer level) {
        //获取所有数据信息
        Map<Integer, List<Rights>> map = getMap();
        //1、一级商品分类信息
        if (level == 1) {
            return map.get(0);
        }
        if (level == 2) {
            //获取一级和二级菜单
            return getTwoList(map);
        }
        //获取一级、二级和三级菜单
        List<Rights> allList = getThreeList(map);
        return allList;
    }
}
