package com.betty.practice.controller;

import com.betty.core.entity.Result;
import com.betty.practice.bean.T;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Betty
 * @date 2021年06月15日
 */
@RestController
@RequestMapping("file")
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@SuppressWarnings("rawtypes")
public class FileController {

    @PostMapping("save")
    public Result save(T form) {
        log.info("form:{}", form);
        return Result.success("yes");
    }

}
