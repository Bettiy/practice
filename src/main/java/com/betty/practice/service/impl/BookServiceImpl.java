package com.betty.practice.service.impl;

import com.betty.practice.bean.Book;
import com.betty.practice.mapper.BookMapper;
import com.betty.practice.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Betty
 * @since 2021-07-21
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
