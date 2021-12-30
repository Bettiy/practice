package com.betty.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Betty
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @MapKey("id")
    List<Map<String, String>> findUser();

    List<User> selectAllUser();
}
