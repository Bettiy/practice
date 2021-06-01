package com.betty.practice.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Betty
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private Integer age;
    private String sex;

}
