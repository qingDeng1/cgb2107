package cn.yd.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    //用户查询的数据
    private String query;
    //查询页数
    private Integer pageNum;
    //查询条数
    private Integer pageSize;
    //查询总记录数
    private Long total;
    //分页查询的结果
    private Object rows;
}
