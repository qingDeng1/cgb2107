package cn.yd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

}
