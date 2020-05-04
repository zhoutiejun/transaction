package com.example.transation.service.impl;

import com.example.transation.entity.bean.Account;
import com.example.transation.entity.bean.User;
import com.example.transation.entity.bean.UserExample;
import com.example.transation.entity.bean.UserLoginQuery;
import com.example.transation.mapper.AccountMapper;
import com.example.transation.mapper.UserMapper;
import com.example.transation.service.UserService;
import com.example.transation.util.Md5Util;
import com.example.transation.util.SessionUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @className: UserServiceImpl
 * @author: xzh
 * @createTime: 2020/4/6 17:37
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private HttpSession session;

    private Cache<String, String> codeMap = CacheBuilder.newBuilder()
            .expireAfterWrite(10L, TimeUnit.MINUTES)
            .build();

    @Override
    public User login(UserLoginQuery query) {
        String md5 = Md5Util.MD5(query.getPassword());
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(query.getUsername()).andPasswordEqualTo(md5);
        List<User> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public String modifyPwd(String newPwd, String oldPwd) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return "请求出错";
        }
        String md5Old = Md5Util.MD5(oldPwd);
        if (md5Old.equals(user.getPassword())) {
            String newMd5 = Md5Util.MD5(newPwd);
            user.setPassword(newMd5);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(1800);
            User u = new User();
            u.setId(user.getId());
            u.setPassword(newMd5);
            int res = userMapper.updateByPrimaryKeySelective(u);
            if (res > 0) {
                return "";
            } else {
                return "修改密码失败";
            }
        } else {
            return "原密码输入错误，请查证后修改";
        }
    }

    @Override
    public String update(String gender, String birthday, String phone, String email, String remark, String realName) {
        User user = SessionUtil.getSessionUser(session);
        User u = new User();
        u.setId(user.getId());
        u.setGender(gender);
        u.setBirthday(birthday);
        u.setPhone(phone);
        u.setEmail(email);
        u.setRemark(remark);
        u.setRealName(realName);
        int res = userMapper.updateByPrimaryKeySelective(u);
        if (res >= 1) {
            User u1 = userMapper.selectByPrimaryKey(user.getId());
            session.setAttribute("user", u1);
            session.setMaxInactiveInterval(1800);
            return "";
        } else {
            return "更新失败";
        }
    }

    @Override
    public String register(UserLoginQuery query) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(query.getUsername());
        long count = userMapper.countByExample(example);
        if (count >= 1L) {
            return "用户已经存在";
        }
        String md5 = Md5Util.MD5(query.getPassword());
        User user = new User();
        user.setGender("");
        user.setAdmin(false);
        user.setUsername(query.getUsername());
        user.setPassword(md5);
        userMapper.insertSelective(user);

        // 默认账户50000 本金
        Account account = new Account();
        account.setAmount(new BigDecimal(50000));
        account.setShareNum("RMB");
        account.setUserId(user.getId());
        accountMapper.insertSelective(account);
        return "";
    }

    @Override
    public User info(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userList)) {
            User user = userList.get(0);
            if (user.getGender() == null) {
                user.setGender("");
            }
            return user;
        }
        return null;
    }

    @Override
    public List<User> list() {
        return userMapper.selectByExample(null);
    }
}
