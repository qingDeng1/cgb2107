package cn.yd.service;

import cn.yd.mapper.ItemCatMapper;
import cn.yd.pojo.ItemCat;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.*;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> findAll(ItemCat itemCat) {
        return itemCatMapper.findAll(itemCat);
    }

    /*
     * 弊端：由于多次循环遍历，查询数据库，导致数据库查询次数太多，效率极低
     * */
//    @Override
//    public List<ItemCat> findItemCatList(Integer level) {
//        //查询一级商品分类信息
//        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", 0);
//        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
//        List<ItemCat> oneList2 = itemCatMapper.findOne(level);
//
//        //查询二级商品分类信息
//        for (ItemCat oneItemCat : oneList) {
//            //为了复用条件构造器将之前的数据清空
//            queryWrapper.clear();
//            queryWrapper.eq("parent_id", oneItemCat.getId());
//            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
//            //遍历二级列表 查询三级数据，封装数据返回
//            for (ItemCat twoItemCat : twoList) {
//                queryWrapper.clear();
//                queryWrapper.eq("parent_id", twoItemCat.getId());
//                List<ItemCat> threeList = itemCatMapper.selectList(queryWrapper);
//                twoItemCat.setChildren(threeList);
//            }
//            //将二级封装到一级
//            oneItemCat.setChildren(twoList);
//        }
//        return oneList;
//    }

    /*
     * 优化手段：思路:获取所有的数据库记录，之后按照父子级关系进行封装
     *      数据结构：Map<k,v>
     *              Map<parentId,LIst当前父级的子集信息(不嵌套)>、
     *      例子：   Map<0,List[{id,name="xx",children=null}...]>
     * 封装数据规则:
     *      1、遍历所有的数据
     *      2、获取parentId
     *      3、判断
     * */
    public Map<Integer, List<ItemCat>> getMap() {
        Map<Integer, List<ItemCat>> map = new HashMap<>();
        //null,查询数据库中所有的数据
        List<ItemCat> list = itemCatMapper.selectList(null);
        //1、遍历数据
        for (ItemCat itemCat : list) {
            int parentId = itemCat.getParentId();
            //containsKey()判断key是否存在
            if (map.containsKey(parentId)) {
                //key存在，把数据追加到list集合中
                map.get(parentId).add(itemCat);
            } else {
                //key不存在，定义list集合，将自己作为第一个元素追加
                List<ItemCat> childrenList = new ArrayList<>();
                childrenList.add(itemCat);
                //将数据保存到map集合中
                map.put(parentId, childrenList);
            }
        }
        return map;
    }

    //该方法获取1-2级数据信息
    public List<ItemCat> getTwoList(Map<Integer, List<ItemCat>> map) {
        //1、先查询一级菜单数据
        List<ItemCat> oneList = map.get(0);
        //2、遍历每个一级菜单去封装二级数据
        for (ItemCat oneItemCat : oneList) {
            //查询二级数据
            int parentId = oneItemCat.getId();
            //查询二级的数据
            List<ItemCat> twoList = map.get(parentId);
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
    public List<ItemCat> getThreeList(Map<Integer, List<ItemCat>> map) {
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
        List<ItemCat> oneList = getTwoList(map);
        //2、遍历一级菜单，获取二级数据
        for (ItemCat oneItemCat : oneList) {
            List<ItemCat> twoList = map.get(oneItemCat.getId());
            //3、根据二级菜单查询三级数据 防止二级数据为null的现象
            if (twoList == null || twoList.size() == 0) {
                /*
                 * twoList == null || twoList.size() == 0 顺序不能乱写，null在前
                 * 如果twoList.size()在前，当twoList为null时，null.size()又会报错
                 * */
                //跳过本次循环，进入下一次循环
                continue;
            }
            for (ItemCat twoItemCat : twoList) {
                //查询三级的数据
                List<ItemCat> threeList = map.get(twoItemCat.getId());
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
    public List<ItemCat> findItemCatList(Integer level) {
        //测试时间
        long startTime = System.currentTimeMillis();
        //获取所有数据信息
        Map<Integer, List<ItemCat>> map = getMap();
        //1、一级商品分类信息
        if (level == 1) {
            return map.get(0);
        }
        if (level == 2) {
            //获取一级和二级菜单
            return getTwoList(map);
        }
        //获取一级、二级和三级菜单
        List<ItemCat> allList = getThreeList(map);
        long enTime = System.currentTimeMillis();
        System.out.println("优化耗时：" + (enTime - startTime));
        return allList;
    }

    @Override
    @Transactional
    public void updateStatusById(ItemCat itemCat) {
        itemCatMapper.updateStatusById(itemCat);
    }

    //优化自动优化时间
    @Override
    @Transactional
    public void saveItemCat(ItemCat itemCat) {
        Date date = new Date();
        itemCat.setStatus(true).setCreated(date).setUpdated(date);
        itemCatMapper.insert(itemCat);
    }

    /*
     * 业务：如果是父级 则应该删除子级和自己
     * 思路：
     *   1、判断是否为3级标签 直接删除
     *   2、判断是否为2级标签 先删除三级再删除二级
     *   3、判断是否为1级标签 先查询二级，再删除三级，再删除二级
     * */
    @Override
    public void deleteItemCat(ItemCat itemCat) {
        if (itemCat.getLevel() == 3) {
            int id = itemCat.getId();
            itemCatMapper.deleteById(id);
            return;
        }
        if (itemCat.getLevel() == 2) { //id=二级id
            //Sql: xxx where parent_id = 二级id
            int id = itemCat.getId();
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            itemCatMapper.delete(queryWrapper);//删除的是三级数据
            //再删除自己
            itemCatMapper.deleteById(id);
            return;
        }

        /*
        * 删除一级菜单
        * */
        //1、查询二级数据
        QueryWrapper queryWrapper = new QueryWrapper();
        int id = itemCat.getId();
        queryWrapper.eq("parent_id",id);
        //由于是删除的业务，只需要获取id即可 所以使用objs
        List list = itemCatMapper.selectObjs(queryWrapper);
        //根据二级Id删除三级数据 SQl xx where parent_id in (1,2,3)
        if (list.size() > 0) {
            queryWrapper.clear();
            queryWrapper.in("parent_id", list);
            itemCatMapper.delete(queryWrapper);
            //删除二级和一级
            //将所有的一二级的Id封装到一个集合中
            list.add(id);
            itemCatMapper.deleteBatchIds(list);
        } else {
            itemCatMapper.deleteById(id);
        }
    }

    @Override
    public void updateItemCat(ItemCat itemCat) {
        //Date date = new Date();
        //itemCat.setStatus(true);
                /*setCreated(date).
                setUpdated(date);*/
        itemCatMapper.updateItemCat(itemCat);
    }
}
