package com.betty.practice.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.betty.core.entity.Result;
import com.betty.practice.bean.User;
import com.betty.practice.service.AsyncService;
import com.betty.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Betty
 */
@RestController
@RequestMapping("test")
@Slf4j
@SuppressWarnings("rawtypes")
public class TestController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("t")
    public String tt() {
        ScheduledExecutorService service = SpringUtil.getBean("scheduledExecutorService");
        System.out.println(service);
        return "fff";
    }

    @GetMapping("url")
    public String api(String name) {
        return "hello," + name;
    }

    @GetMapping("test1")
    public String test1(@RequestBody User user) {
        log.info("user:{}", user);
        return user.toString();
    }

    @GetMapping("/async")
    public void async() {
        asyncService.executeAsync();
    }


    @Autowired
    private UserService userService;

    @GetMapping("user")
    public Result test2() {
        return Result.data(userService.findUser());
    }

	@GetMapping("log")
	public void log4j2Test() {
		log.info("hello");
		System.out.println("==============");
	}

}
