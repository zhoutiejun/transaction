package com.example.transation.util;

import com.example.transation.entity.bean.User;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @className: SessionUtil
 * @author: xzh
 * @createTime: 2020/4/6 19:18
 **/
public class SessionUtil {

    /**
     * 获取当前登录用户
     *
     * @param session
     * @return
     */
    public static User getSessionUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
