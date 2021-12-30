package com.betty.practice.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Betty
 * @date 2021年12月21日
 */
@Data
@TableName("person_info")
public class DataDetail {
	private Integer id;
	private String sourceData;
	private String interfaceData;
}
