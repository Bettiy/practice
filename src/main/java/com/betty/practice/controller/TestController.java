package com.betty.practice.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.betty.practice.bean.User;
import com.betty.practice.service.AsyncService;
import com.td.ai.frame.common.apisystem.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Betty
 */
@RestController
@RequestMapping("api")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @Resource
    private HttpUtil httpUtil;

    @Autowired
    private AsyncService asyncService;

    @GetMapping("out")
    public String out(@RequestBody Object params){
        Header[] headers = {new BasicHeader("Content-Type" , "application/json")};
        return httpUtil.doPost("http://10.183.7.22:8010/MerchantImp/httpservice/outSysStoreService/queryOutStoreInfoByCode",
                headers, JSON.toJSONString(params), "2");
    }

    @GetMapping("t")
    public String tt() {
        Object scheduledExecutorService = SpringUtil.getBean("scheduledExecutorService");
        System.out.println(scheduledExecutorService);
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
