package com.betty.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.betty.practice.bean.Blog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
public interface BlogService extends IService<Blog> {

	List<?> selectAllBlog();

}
