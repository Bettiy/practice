package com.betty.practice.bean;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Betty
 * @date 2021年07月21日
 */
@Data
@ToString
public class Role implements Serializable {

    private Long id;
    private String roleName;
    private Date createTime;
    private Date updateTime;

}
