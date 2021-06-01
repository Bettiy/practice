package com.betty.practice;

import com.betty.practice.bean.User;
import com.betty.practice.dao.UserMapper;
import com.betty.practice.utils.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author Betty
 */
@SpringBootTest
@Slf4j
public class PracticeApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //由于之前已经自定义注入RedisTemplate组件，因而在此可以直接自动装配
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //采用RedisTemplate将字符串信息写入缓存中并读取出来
    @Test
    public void one() {
        log.info("------开始RedisTemplate操作组件实战----");
        //定义字符串内容及存入缓存的key
        final String key = "redis:template:userinfo";
        final String content = "你好，李焕英";
        //Redis通用的操作组件
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{} ", content);
        valueOperations.set(key, content, 1, TimeUnit.DAYS);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        log.info("读取出来的内容：{} ", result);
    }

    @Test
    public void two(){
        User user = new User(null, "张三", 16, "女");
        int insert = userMapper.insert(user);
    }

    @Test
    public void three() {
        //定义字符串内容及存入缓存的key
        final String key = "redis:template:userinfo:MrLi";
        final String content = "你好，李焕英";
        redisUtils.set(key, content);
        log.info("读取出来的内容：{} ", redisUtils.get(key));
    }

    @Test
    void contextLoads() {

    }

}
