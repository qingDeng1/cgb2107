package cn.yd.service;

import cn.yd.pojo.ItemCat;
import cn.yd.pojo.Rights;

import java.util.List;

public interface RightsService {

    List<Rights> getRightsList();


    List<Rights> findRightsList(Integer level);
}
