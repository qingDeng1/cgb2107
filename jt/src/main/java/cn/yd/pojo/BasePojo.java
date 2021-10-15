package cn.yd.pojo;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain=true)
public class BasePojo implements Serializable{
	//新增操作时自动填充
	@TableField(fill = FieldFill.INSERT)
	private Date created;	//表示入库时需要赋值
	//新增和修改时自动填充
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updated;	//表示入库/更新时赋值.
}
