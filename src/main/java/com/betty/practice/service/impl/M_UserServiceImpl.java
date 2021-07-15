package com.betty.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betty.practice.bean.M_User;
import com.betty.practice.dao.M_UserMapper;
import com.betty.practice.service.M_UserService;
import org.springframework.stereotype.Service;

/**
 * @author Betty
 * @date 2021年07月13日
 */
@Service
public class M_UserServiceImpl extends ServiceImpl<M_UserMapper, M_User> implements M_UserService {
}
