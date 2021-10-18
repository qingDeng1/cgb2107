package cn.yd.mapper;

import cn.yd.pojo.Rights;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RightsMapper extends BaseMapper<Rights> {

    List<Rights> getRightsList();

    List<Rights> getRightsList2();


}
