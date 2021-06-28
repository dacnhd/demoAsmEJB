package com.example.demoasmejb.controller;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Credential;
import com.example.demoasmejb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account account){
        return accountService.create(account);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Credential login(@RequestParam String username, @RequestParam String password){
        return accountService.login(username, password);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public Account accountByToken(@RequestParam String token){
        return accountService.findAccountByToken(token);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public Account profile(){
        return accountService.profile();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        return accountService.logout();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getDetail(@PathVariable long id){
        return accountService.getUser(id);
    }
}
