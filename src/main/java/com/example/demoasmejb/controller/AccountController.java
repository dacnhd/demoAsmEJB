package com.example.demoasmejb.controller;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam int role, @RequestParam int status){
        Account account = new Account();
        account.setUsername(username);
        account.setPasswordHash(password);
        account.setRole(role);
        account.setStatus(status);
        accountService.create(account);
        return "index";
    }
}
