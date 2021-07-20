package com.betty.practice.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Betty
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
