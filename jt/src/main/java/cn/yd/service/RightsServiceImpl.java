package cn.yd.service;

import cn.yd.mapper.RightsMapper;
import cn.yd.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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


}
