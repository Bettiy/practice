package com.betty.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betty.practice.bean.User;
import com.betty.practice.mapper.UserMapper;
import com.betty.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Betty
 * @date 2021年04月30日
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper mapper;

    @Override
    public List<Map<?, ?>> findUser() {
        return mapper.findUser();
    }

    @Override
    public List<User> selectAllUser() {
        return mapper.selectAllUser();
    }
}
