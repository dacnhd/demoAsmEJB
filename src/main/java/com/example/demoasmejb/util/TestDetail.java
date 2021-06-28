package com.example.demoasmejb.util;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Product;
import com.example.demoasmejb.service.AccountService;
import com.example.demoasmejb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDetail {

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    public void data() {
        if (accountService.getList().size() == 0) {
            accountService.create(new Account("admin", "12345",1,1));
            accountService.create(new Account("user", "12345",0,1));
        }
        if (productService.getList().size() == 0) {
            productService.create(new Product("aaa1", 10000));
            productService.create(new Product("aaa2", 20000));
            productService.create(new Product("aaa3", 30000));
            productService.create(new Product("aaa4", 40000));
            productService.create(new Product("aaa5", 50000));
            productService.create(new Product("aaa6", 60000));
            productService.create(new Product("aaa7", 70000));
            productService.create(new Product("aaa8", 80000));
            productService.create(new Product("aaa9", 90000));
            productService.create(new Product("aaa10", 100000));
        }
    }
}
