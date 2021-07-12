package com.betty.practice.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.betty.practice.bean.User;
import com.betty.practice.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Betty
 */
@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
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
    public String api(String name){
        return "hello," + name;
    }

    @GetMapping("test1")
    public String test1(@RequestBody User user){
        log.info("user:{}", user);
        return user.toString();
    }

    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }

}
