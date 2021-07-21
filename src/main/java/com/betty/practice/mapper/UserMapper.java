package com.betty.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author Betty
 */
public interface UserMapper extends BaseMapper<User> {
    @MapKey("id")
    List<Map<?, ?>> findUser();

    List<User> selectAllUser();
}
