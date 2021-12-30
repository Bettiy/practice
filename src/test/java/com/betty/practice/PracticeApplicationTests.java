package com.betty.practice;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.betty.practice.bean.DataDetail;
import com.betty.practice.bean.User;
import com.betty.practice.mapper.InfoMapper;
import com.betty.practice.mapper.UserMapper;
import com.betty.practice.service.UserService;
import com.betty.practice.utils.redis.RedisUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Betty
 */
@SpringBootTest
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked"})
public class PracticeApplicationTests {

    @Resource
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
    public void three() {
        //定义字符串内容及存入缓存的key
        final String key = "redis:template:userinfo:MrLi";
        final String content = "你好，李焕英";
        redisUtils.set(key, content);
        log.info("读取出来的内容：{} ", redisUtils.get(key));
    }

    @Autowired
    UserService userService;

    @Test
    @SneakyThrows
    void contextLoads() {
        User user = new User().setId(1L).setUsername("凹凸曼");
        userService.updateById(user);
        User user1 = new User().setId(1L).setUsername("铁猴子");
        userService.updateById(user1);
    }

	@Autowired
	InfoMapper infoMapper;

	@Test
	void read() {
		File file = new File("D:\\个人客户基本信息.xlsx");
		ExcelReader excelReader = ExcelUtil.getReader(file, "码值");
		List<DataDetail> list = excelReader.readAll(DataDetail.class);
		infoMapper.insertList(list);
	}

}
