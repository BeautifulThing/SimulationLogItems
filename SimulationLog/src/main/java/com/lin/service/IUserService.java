package com.lin.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService  {
    String login(HttpServletResponse response, String username, String password);

    Page<User> findUserPage(Page<User> page);

    User findById(Long id);

    Integer deleteById(Long id);

    Integer update(User user);

    User getByToken(String token);

    User getByToken(HttpServletResponse response, String token);

    User getLoginUser(HttpServletRequest request);

    User getLoginUser(HttpServletRequest request, HttpServletResponse response);
}
