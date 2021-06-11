package com.betty.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Betty
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
