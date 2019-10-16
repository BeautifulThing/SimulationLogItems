package com.lin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Book;

import java.util.List;

public interface IBookService {
    List<Book> findAll();

    Book findById(Long id);

    Page<Book> findPage(Page<Book> page);

    Integer update(Book book);

    Integer insert(Book book);

    Integer delete(Long id);
}
