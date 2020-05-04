package com.example.transation.service.impl;

import com.example.transation.entity.bean.Account;
import com.example.transation.entity.bean.AccountExample;
import com.example.transation.entity.bean.User;
import com.example.transation.mapper.AccountMapper;
import com.example.transation.service.AccountService;
import com.example.transation.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 2020/4/25 下午9:33
 * @MOdified by:
 **/
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private HttpSession session;

    @Override
    public List<Account> list() {
        User user = SessionUtil.getSessionUser(session);
        AccountExample example = new AccountExample();
        example.createCriteria().andUserIdEqualTo(user.getId());
        return accountMapper.selectByExample(example);
    }

    @Override
    public Account getAccountByUserIdAndShareNum(Integer userId, String shareNum) {
        AccountExample example = new AccountExample();
        example.createCriteria().andUserIdEqualTo(userId).andShareNumEqualTo(shareNum);

        List<Account> accountList = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(accountList)){
            return null;
        }

        return accountList.get(0);
    }
}
