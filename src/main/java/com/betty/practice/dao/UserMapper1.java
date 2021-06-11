package com.betty.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.User1;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Betty
 * @date 2021年04月30日
 */
@Mapper
public interface UserMapper1 extends BaseMapper<User1> {
}
