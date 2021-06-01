package com.betty.practice.controller;

import com.betty.practice.bean.User;
import com.betty.practice.dao.UserMapper;
import com.betty.practice.service.UserService;
import com.betty.practice.target.AccessLimit;
import com.betty.practice.utils.redis.RedisUtils;
import com.betty.practice.utils.result.CommonEnum;
import com.betty.practice.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class HelloController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @AccessLimit(seconds = 3600, maxCount = 5)
    @ApiOperation("测试接口1")
    @GetMapping("hello")
    public Result hello(@ApiParam(name = "测试参数姓名") String name) {
        User user = new User(null,"张三", 18, "女");
        log.info("hello,{}是一个憨憨", user.getUsername());
        return Result.success(CommonEnum.SUCCESS, user);
    }

    @AccessLimit(seconds=60, maxCount=5)
    @ApiOperation("测试接口2")
    @GetMapping("test")
    public Result test(){
        User user = new User(null,"张三", 14, "男");
        int i = 1/0;
        return Result.success("张三测试了test接口",user);
    }

    @ApiOperation("测试接口3")
    @GetMapping("test1")
    public Result<List<Map<String, Object>>> test1(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("A_NAME", "成都");
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(dataMap);
        return Result.success(list);
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
        return Result.success(CommonEnum.SUCCESS, user);
    }

    @ApiOperation("查询所有用户")
    @GetMapping("findAllUser")
    public Result<List<User>> findAllUser() {
        List<User> userList = (List<User>) redisUtils.get("test:user:userinfo");
        if (userList == null) {
            log.info("从数据库读取数据");
            userList = userMapper.selectList(null);
            redisUtils.set("test:user:userinfo", userList, 60L);
        }
        return Result.success(CommonEnum.SUCCESS, userList);
    }

    @ApiOperation("测试redis")
    @GetMapping("save/{k}/{v}")
    public Result save(@PathVariable("k") String key, @PathVariable("v") String value) {
        //定义字符串内容及存入缓存的key
        redisUtils.set(key, value, 120L);
        log.info("读取出来的内容：{} ", redisUtils.get(key));
        return Result.success(CommonEnum.SUCCESS, redisUtils.get(key));
    }

    @ApiOperation("测试事务")
    @GetMapping("saveUser")
    public Result saveUser() throws Exception {
        userService.save();
        return Result.success();
    }

}
