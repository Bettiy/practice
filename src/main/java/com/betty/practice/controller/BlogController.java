package com.betty.practice.controller;


import com.betty.core.entity.Result;
import com.betty.practice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/blog")
@SuppressWarnings("rawtypes")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@GetMapping("selectAllBlog")
	public Result selectAllBlog() {
		List<?> map = blogService.selectAllBlog();
		return Result.data(map);
	}
}

