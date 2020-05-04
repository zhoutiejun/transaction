package com.example.transation.entity.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @className: UserLoginQuery
 * @author: xzh
 * @createTime: 2020/4/6 18:34
 **/
@Data
public class UserLoginQuery {

    @NotBlank(message = "账户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
