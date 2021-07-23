package com.betty.practice.mapper;

import com.betty.practice.bean.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
