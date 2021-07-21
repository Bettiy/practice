package com.betty.practice.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.betty.core.entity.Result;
import com.betty.practice.bean.User;
import com.betty.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@SuppressWarnings("rawtypes")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("lambda")
    public Result test1(String age) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(User::getAge, age);
        List<User> users = userService.list();
        List<User> userList = users.stream().filter(user -> user.getAge() > 21).collect(Collectors.toList());
        return Result.data(userList);
    }

    @GetMapping("slave")
    @DS("slave")
    public Result test2() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from blade_user");
        return Result.data(mapList);
    }

    @GetMapping("slave1")
    @DS("slave")
    public Result test3() {
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from sys_user");
        return Result.data(mapList);
    }

    @GetMapping("update")
    public Result test4() {
        User user = new User();
        user.setId(8L);
        user.setUsername("菲律宾铁猴子");
        user.setAge(20);
        user.setSex("男");
        return Result.status(userService.updateById(user));
    }

    @GetMapping("update1")
    public Result test5() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("age", 500);
        updateWrapper.eq("id", 8);
        return Result.status(userService.update(updateWrapper));
    }

    @GetMapping("save")
    public Result save() {
        User user = new User();
        user.setUsername("龙开环");
        user.setAge(12);
        user.setSex("未知");
        return Result.status(userService.save(user));
    }

    @GetMapping("selectAllUser")
    public Result selectAllUser() {
        return Result.data(userService.selectAllUser());
    }

}
