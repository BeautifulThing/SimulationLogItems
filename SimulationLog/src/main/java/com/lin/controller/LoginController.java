package com.lin.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.common.annotation.SystemLog;
import com.lin.common.result.ResponseResult;
import com.lin.entity.Log;
import com.lin.enums.LogType;
import com.lin.service.ILogService;
import com.lin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
@RestController
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ILogService logService;
    @PostMapping("/doLogin")
    @SystemLog(description = "用户登录", type = LogType.LOGIN)
    public ResponseResult<String> doLogin(HttpServletResponse response,
                                          @RequestParam("username") String username,
                                          @RequestParam("password") String password) {
        // 用户登录逻辑，返回token
        String token = userService.login(response, username, password);
        return ResponseResult.success(token);
    }
}
