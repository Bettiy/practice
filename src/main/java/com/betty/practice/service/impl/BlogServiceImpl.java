package com.betty.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.betty.practice.annotation.Log;
import com.betty.practice.bean.Blog;
import com.betty.practice.mapper.BlogMapper;
import com.betty.practice.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

	@Resource
	private BlogMapper blogMapper;

	public List<?> selectAllBlog() {
		return blogMapper.selectAllBlog();
	}

}
