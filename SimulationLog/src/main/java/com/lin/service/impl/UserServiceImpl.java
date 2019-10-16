package com.lin.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.common.exception.GlobalException;
import com.lin.common.result.CodeMsg;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.IUserService;
import com.lin.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    /**
     * token过期时间,2天
     */
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(HttpServletResponse response, String username, String password) {
        User user = userMapper.findByUsername(username);
        if(null == user){
            throw new GlobalException(CodeMsg.USERNAME_NOT_EXIST);
        }
        if(!password.equals(user.getPassword())){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUID.randomUUID().toString().replace("-", "");
        addCookie(response, token, user);
        return token;
    }

    @Override
    public Page<User> findUserPage(Page<User> page) {
        return userMapper.findUserPage(page);
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        // 将token存入到redis
        redisUtil.set(COOKIE_NAME_TOKEN + "::" + token, JSON.toJSONString(user), TOKEN_EXPIRE);
        // 将token写入cookie
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return getLoginUser(request, null);
    }


    @Override
    public User getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求参数的 tokent 值
        String paramToken = request.getParameter(UserServiceImpl.COOKIE_NAME_TOKEN);
        // 获取 Cookie 值
        String cookieToken = getCookieValue(request, UserServiceImpl.COOKIE_NAME_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
        }
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        if (response == null) {
            return getByToken(token);
        }
        return getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            throw new GlobalException(CodeMsg.TOKEN_INVALID);
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    @Override
    public User findById(Long id) {
        return  userMapper.selectById(id);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = JSON.parseObject(redisUtil.get(COOKIE_NAME_TOKEN + "::" + token), User.class);
        // 重置有效期
        if (user == null) {
            throw new GlobalException(CodeMsg.USER_NOT_LOGIN);
        }
        if (response != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    @Override
    public User getByToken(String token) {
        return getByToken(null, token);
    }
}
