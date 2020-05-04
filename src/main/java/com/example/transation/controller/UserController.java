package com.example.transation.controller;

import com.example.transation.common.Result;
import com.example.transation.entity.bean.User;
import com.example.transation.entity.bean.UserLoginQuery;
import com.example.transation.enums.CodeEnum;
import com.example.transation.exception.BusinessException;
import com.example.transation.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @description:
 * @className: UserController
 * @author: xzh
 * @createTime: 2020/4/6 17:36
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("login")
    public Result<String> login(@Valid UserLoginQuery query) {
        try {
            User loginUser = userService.login(query);
            if (loginUser == null) {
                return Result.failure(CodeEnum.USER_LOGIN_FAILURE);
            }
            session.setAttribute("user", loginUser);
            session.setMaxInactiveInterval(1800);
            return Result.success();
        } catch (BusinessException e) {
            return Result.failure(e.getExceptionCodeEnum());
        }
    }

    @RequestMapping("password")
    public Result<String> password(String newPwd, String oldPwd) {
        try {
            if (StringUtils.isBlank(newPwd) || StringUtils.isBlank(oldPwd)) {
                return Result.failure(1001, "密码不能为空");
            }
            String res = userService.modifyPwd(newPwd, oldPwd);
            if (StringUtils.isBlank(res)) {
                return Result.success();
            }
            return Result.failure(1001, res);
        } catch (BusinessException e) {
            return Result.failure(e.getErrorCode(), e.getMessage());
        }
    }

    @PostMapping("logout")
    public Result<String> logout() {
        session.removeAttribute("user");
        return Result.success();
    }

    @RequestMapping("update")
    public Result<String> update(String gender, String birthday, String phone, String email, String remark, String realName) {
        try {
            String msg = userService.update(gender, birthday, phone, email, remark, realName);
            if (StringUtils.isBlank(msg)) {
                return Result.success();
            } else {
                return Result.failure(1, msg);
            }
        } catch (BusinessException e) {
            return Result.failure(e.getErrorCode(), e.getMessage());
        }
    }

    @RequestMapping("reg")
    public Result<String> reg(@Valid UserLoginQuery query) {
        try {
            String res = userService.register(query);
            if (StringUtils.isNotBlank(res)){
                return Result.failure(1,res);
            }
            return Result.success();
        } catch (Exception e) {
            return Result.failure();
        }
    }

    @RequestMapping("info")
    public Result<User> info(String username){
        if (StringUtils.isBlank(username)){
            return Result.failure();
        }
        return Result.success(userService.info(username));
    }

}
