package com.example.transation.controller;

import com.example.transation.common.Result;
import com.example.transation.entity.bean.Account;
import com.example.transation.entity.bean.User;
import com.example.transation.service.AccountService;
import com.example.transation.service.TransactionService;
import com.example.transation.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 2020/4/25 下午10:13
 * @MOdified by:
 **/
@Controller
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpSession session;

    @RequestMapping("buy")
    public String buy(String shareNum, Model model) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return "redirect:/page/login";
        }
        Account account = accountService.getAccountByUserIdAndShareNum(user.getId(), shareNum);
        if (account == null) {
            account = new Account();
            account.setAmount(BigDecimal.ZERO);
            account.setShareNum(shareNum);
            account.setUserId(user.getId());
        }
        model.addAttribute("account", account);
        return "accountBuy";
    }

    @RequestMapping("sub")
    public String sub(String shareNum, Model model) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return "redirect:/page/login";
        }
        Account account = accountService.getAccountByUserIdAndShareNum(user.getId(), shareNum);
        if (account == null) {
            account = new Account();
            account.setAmount(BigDecimal.ZERO);
            account.setShareNum(shareNum);
            account.setUserId(user.getId());
        }
        model.addAttribute("account", account);
        return "accountSub";
    }

    @RequestMapping("buyIn")
    @ResponseBody
    public Result<String> buyIn(String shareNum, BigDecimal amount) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return Result.failure(1, "请登录后再进行操作");
        }
        String res = transactionService.buyShare(shareNum, amount, user.getId());
        if (StringUtils.isNotBlank(res)){
            return Result.failure(1,res);
        }
        return Result.success();
    }

    @RequestMapping("subOut")
    @ResponseBody
    public Result<String> buyOut(String shareNum, BigDecimal amount) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return Result.failure(1, "请登录后再进行操作");
        }
        String res = transactionService.sellShare(shareNum, amount, user.getId());
        if (StringUtils.isNotBlank(res)){
            return Result.failure(1,res);
        }
        return Result.success();
    }
}
