package com.betty.practice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author Betty
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
