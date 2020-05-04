package com.example.transation.service;

import com.example.transation.entity.bean.User;
import com.example.transation.entity.bean.UserLoginQuery;

import java.util.List;


/**
 * @description:
 * @className: UserService
 * @author: xzh
 * @createTime: 2020/4/6 17:37
 **/
public interface UserService {

    /**
     * 登录
     *
     * @param query
     * @return
     */
    User login(UserLoginQuery query);

    String register(UserLoginQuery query);

    User info(String username);

    /**
     * 修改密码
     *
     * @param newPwd
     * @param oldPwd
     * @return
     */
    String modifyPwd(String newPwd, String oldPwd);

    String update(String gender, String birthday, String phone, String email, String remark, String realName);

    List<User> list();
}
