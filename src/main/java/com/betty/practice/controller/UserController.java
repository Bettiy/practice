package com.betty.practice.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.betty.practice.bean.User;
import com.betty.practice.service.M_UserService;
import com.betty.practice.service.UserService;
import com.betty.practice.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Betty
 * @date 2021年07月13日
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private M_UserService mUserService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("ge")
    public Result test1(String age) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getAge, age);
        List<User> users = userService.list();
        List<User> userList = users.stream().filter(user -> user.getAge() > 21).collect(Collectors.toList());
        return Result.success(userList);
    }

    @GetMapping("ds")
    @DS("slave_1")
    public Result test2() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from blade_user");
        return Result.success(mapList);
    }

}
