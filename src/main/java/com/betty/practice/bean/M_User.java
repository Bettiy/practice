package com.betty.practice.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Betty
 * @date 2021年04月30日
 */
@Data
@TableName("m_user")
@AllArgsConstructor
@NoArgsConstructor
public class M_User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private Integer status;
    private Date created;
    private Date last_login;
}
