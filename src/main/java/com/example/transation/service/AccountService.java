package com.example.transation.service;

import com.example.transation.entity.bean.Account;

import java.util.List;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 2020/4/25 下午9:33
 * @MOdified by:
 **/
public interface AccountService {

    List<Account> list();

    Account getAccountByUserIdAndShareNum(Integer userId,String shareNum);
}
