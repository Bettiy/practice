package com.betty.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.betty.practice.bean.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

	List<?> selectAllBlog();

}
