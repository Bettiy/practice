package com.betty.practice.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Betty
 */
@Data
@ToString
@TableName("user")
@Accessors(chain = true)
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private Integer age;
    private String sex;
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    private transient List<Role> role;

}
