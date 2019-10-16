package com.lin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.common.annotation.SystemLog;
import com.lin.common.result.ResponseResult;
import com.lin.entity.User;
import com.lin.enums.LogType;
import com.lin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/current")
    @ResponseBody
    public ResponseResult<User> current(User user) {
        return ResponseResult.success(user);
    }

    @GetMapping
    @SystemLog(description = "查询用户列表", type = LogType.OPERATION)
    public ResponseResult<Page<User>> users(@RequestParam(value = "currentPage", defaultValue = "0") Integer currentPage,
                                             @RequestParam(value = "currentSize", defaultValue = "10") Integer currentSize, Model model) {
        Page<User> page = new Page<>(currentPage,currentSize);
        return ResponseResult.success(userService.findUserPage(page));
    }

    @PutMapping
    public ResponseResult<Boolean> update(@RequestBody User user) {
        return (userService.update(user) > 0)?ResponseResult.success(true):ResponseResult.success(false);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Boolean> delete(@PathVariable("id") Long id) {
        return (userService.deleteById(id) > 0)?ResponseResult.success(true):ResponseResult.success(false);
    }

    @GetMapping("/{id}")
    public ResponseResult<User> get(@PathVariable("id") Long id) {
        return ResponseResult.success(userService.findById(id));
    }
}
