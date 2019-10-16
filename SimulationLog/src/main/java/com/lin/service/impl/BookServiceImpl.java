package com.lin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.Book;
import com.lin.mapper.BookMapper;
import com.lin.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<Book> findAll() {
        return  bookMapper.findAll();
    }
    @Override
    public Book findById(Long id) {
        return  bookMapper.selectById(id);
    }

    @Override
    public Page<Book> findPage(Page<Book> page) {
        return bookMapper.findPage(page);
    }


    public Integer insert(Book book){
        return  bookMapper.insert(book);
    }

    @Override
    public Integer delete(Long id) {
        return bookMapper.deleteById(id);
    }

    public Integer update(Book book){
        return  bookMapper.updateById(book);
    }

}
