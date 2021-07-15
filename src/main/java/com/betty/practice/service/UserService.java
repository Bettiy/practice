package com.betty.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.betty.practice.bean.User;

/**
 * @author Betty
 * @date 2021年04月30日
 */
public interface UserService extends IService<User> {
    void save() throws Exception;
}
