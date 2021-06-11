package com.betty.practice.service.impl;

import com.betty.practice.bean.User;
import com.betty.practice.bean.User1;
import com.betty.practice.dao.UserMapper;
import com.betty.practice.dao.UserMapper1;
import com.betty.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Betty
 * @date 2021年04月30日
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper mapper;

    @Resource
    private UserMapper1 mapper1;


    @Override
    public void save() throws Exception{
        try {
            mapper.insert(new User(null, "张三", 18, "男"));

            mapper1.insert(new User1(null, "李四", "1k", "1@qq.com", "ffff", 1, new Date(), new Date()));
            int i = 1/0;
        } catch (Exception e) {
            log.error("捕获异常");
            throw new ArithmeticException("0被作为除数");
        }
    }
}
