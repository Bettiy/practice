package com.betty.practice.controller;

import com.betty.practice.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("url")
    public String api(){
        return "hello 张三";
    }

    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
    }

}
