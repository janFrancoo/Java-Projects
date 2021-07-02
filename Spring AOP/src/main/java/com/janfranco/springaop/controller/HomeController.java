package com.janfranco.springaop.controller;

import com.janfranco.springaop.dao.AccountDAO;
import com.janfranco.springaop.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AccountDAO accountDAO;

    @RequestMapping("/")
    public String home() {
        Account newAccount = new Account("JanFranco");
        List<Account> accountList = accountDAO.addAccount(newAccount);
        for (Account account : accountList) {
            System.out.println(account);
        }
        return "home";
    }

    @RequestMapping("/exception")
    public String exception() {
        try {
            accountDAO.getAccount();
        } catch (Exception ignored) { }
        return "home";
    }

    @RequestMapping("/calculate")
    public String calculate() {
        accountDAO.doWork();
        return "home";
    }
}
