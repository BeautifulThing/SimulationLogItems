package com.lin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.common.annotation.SystemLog;
import com.lin.common.result.ResponseResult;
import com.lin.entity.Book;
import com.lin.enums.LogType;
import com.lin.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;
    @GetMapping("findPage")
    @SystemLog(description = "查询Book分页数据", type = LogType.OPERATION)
    public ResponseResult<Page<Book>> findPage(@RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
                                               @RequestParam(value = "currentSize", defaultValue = "10") Integer currentSize) {
        Page<Book> page = new Page<>(currentPage,currentSize);
        return ResponseResult.success(bookService.findPage(page));
    }
    @GetMapping("findAll")
    @SystemLog(description = "查询Book全部数据", type = LogType.OPERATION)
    public ResponseResult<List<Book>> findAll() {
        return ResponseResult.success(bookService.findAll());
    }

    @PutMapping("update")
    @SystemLog(description = "更新Book", type = LogType.OPERATION)
    public ResponseResult<Boolean> update(@RequestBody Book book) {
        return (bookService.update(book) > 0)?ResponseResult.success(true):ResponseResult.success(false);
    }
    @PostMapping("insert")
    @SystemLog(description = "保存Book", type = LogType.OPERATION)
    public ResponseResult<Boolean> insert(@RequestBody Book book) {
        return (bookService.insert(book) > 0)?ResponseResult.success(true):ResponseResult.success(false);
    }
    @DeleteMapping("/{id}")
    @SystemLog(description = "删除Book", type = LogType.OPERATION)
    public ResponseResult<Boolean> delete(@PathVariable("id") Long id) {
        return (bookService.delete(id)> 0)?ResponseResult.success(true):ResponseResult.success(false);
    }
    @SystemLog(description = "根据ID获取Book", type = LogType.OPERATION)
    @GetMapping("/{id}")
    public ResponseResult<Book> get(@PathVariable("id") Long id) {
        return ResponseResult.success(bookService.findById(id));
    }
}
