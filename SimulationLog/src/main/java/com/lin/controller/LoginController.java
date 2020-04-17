package com.lin.controller;

import com.lin.common.annotation.SystemLog;
import com.lin.common.result.ResponseResult;
import com.lin.enums.LogType;
import com.lin.service.ILogService;
import com.lin.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value = "用户登录接口",httpMethod = "POST",notes = "")
    @PostMapping("/doLogin")
    @SystemLog(description = "用户登录", type = LogType.LOGIN)
    public ResponseResult<String> doLogin(HttpServletResponse response,
                                          @ApiParam(name = "username", value = "用户名称", required = true) @RequestParam("username") String username,
                                          @ApiParam(name = "password", value = "用户密码", required = true)  @RequestParam("password") String password) {
        // 用户登录逻辑，返回token
        String token = userService.login(response, username, password);
        return ResponseResult.success(token);
    }
}
