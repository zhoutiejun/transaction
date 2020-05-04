package com.example.transation.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 11:34 2018/12/4
 * @Modified by:
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {

    SUCCESS(0,"请求成功"),
    FAILURE(1,"请求失败"),

    SYSTEM_ERROR(-1000,"系统服务异常"),
    PAGE_NOT_FOUND(-1001, "接口不存在"),
    SERVLET_FAIL(-1002,"Servlet出错"),
    INTERNAL_SERVER_ERROR(-1003,"服务器内部错误"),

    USER_LOGIN_FAILURE(1001,"登录失败，用户名或密码错误"),

    ;
    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述
     */
    private String message;
}
