package com.example.demoasmejb.controller;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Product;
import com.example.demoasmejb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getList(){
        return productService.getList();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam String name, @RequestParam double price){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productService.create(product);
        return "index";
    }
}
