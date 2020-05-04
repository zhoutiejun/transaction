package com.example.transation.controller;

import com.example.transation.entity.bean.User;
import com.example.transation.service.AccountService;
import com.example.transation.service.TransactionService;
import com.example.transation.service.UserService;
import com.example.transation.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @className: PageController
 * @author: xzh
 * @createTime: 2020/4/6 17:16
 **/
@Controller
public class PageController {

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @RequestMapping({"/", ""})
    public String index(Model model) {
        User user = SessionUtil.getSessionUser(session);
        if (user == null) {
            return "redirect:/page/login";
        }
        return "page/index";
    }

    @RequestMapping("page/{page}")
    public String page(@PathVariable(name = "page") String pageName, Integer id, Model model) {
        User user = SessionUtil.getSessionUser(session);
        if ("profile".equals(pageName)) {
            if (user == null) {
                return "redirect:/page/login";
            }
            session.setAttribute("user", userService.info(user.getUsername()));
        }
        if ("tradeList".equals(pageName)) {
            if (user == null) {
                return "redirect:/page/login";
            }
            model.addAttribute("tradeList", transactionService.list(user.getId()));
        }
        if ("userList".equals(pageName)){
            if (user == null) {
                return "redirect:/page/login";
            }
            model.addAttribute("userList",userService.list());
        }
        if ("accountList".equals(pageName)){
            if (user == null) {
                return "redirect:/page/login";
            }
            model.addAttribute("accountList",accountService.list());
        }
        return pageName;
    }
}
