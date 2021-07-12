package com.betty.practice.bean;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Betty
 * @date 2021年06月15日
 */
@Data
public class T {

    private String name;

    private Integer age;

    private MultipartFile[] file;
}
