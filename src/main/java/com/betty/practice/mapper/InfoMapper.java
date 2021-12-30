package com.betty.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.DataDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Betty
 * @date 2021年12月21日
 */
@Mapper
public interface InfoMapper extends BaseMapper<DataDetail> {

	void insertList(List<DataDetail> list);
}
