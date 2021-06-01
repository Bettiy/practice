package com.betty.practice.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author Betty
 * @date 2021年05月19日
 */
@Data
public class Api {
    private Long apiId;
    private Long userId;
    private String token;
    private Long timestamp;
    private JSONObject data;
}
