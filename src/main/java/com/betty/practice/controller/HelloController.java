package com.betty.practice.controller;

import com.betty.core.entity.Result;
import com.betty.core.entity.ResultCode;
import com.betty.practice.bean.User;
import com.betty.practice.mapper.UserMapper;
import com.betty.practice.service.UserService;
import com.betty.practice.utils.redis.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Betty
 */
@RestController
@RequestMapping("practice")
@Slf4j
@Api(value = "myApi", tags = "API测试接口")
@CrossOrigin(origins = "*", maxAge = 3600)
@SuppressWarnings("rawtypes")
public class HelloController {

    @Autowired
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("测试接口3")
    @GetMapping("test1")
    public Result<List<Map<String, Object>>> test1(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("A_NAME", "成都");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(dataMap);
        return Result.data(list);
    }

    @ApiOperation("添加用户")
    @GetMapping("addUser")
    public Result<User> addUser() {
        User user = new User();
        user.setUsername("王五");
        user.setAge(22);
        user.setSex("女");
        int insert = userMapper.insert(user);
        log.info("添加成功，状态码{}", insert);
        return Result.success(ResultCode.SUCCESS);
    }

    @ApiOperation("查询所有用户")
    @GetMapping("findAllUser")
    @SuppressWarnings("unchecked")
    public Result<List<User>> findAllUser() {
        List<User> userList = (List<User>) redisUtils.get("test:user:userinfo");
        if (userList == null) {
            log.info("从数据库读取数据");
            userList = userMapper.selectList(null);
            redisUtils.set("test:user:userinfo", userList, 60L);
        }
        return Result.data(200, userList, "jj");
    }

    @ApiOperation("测试redis")
    @GetMapping("save/{k}/{v}")
    public Result save(@PathVariable("k") String key, @PathVariable("v") String value) {
        //定义字符串内容及存入缓存的key
        redisUtils.set(key, value, 120L);
        log.info("读取出来的内容：{} ", redisUtils.get(key));
        return Result.data(redisUtils.get(key));
    }

}
