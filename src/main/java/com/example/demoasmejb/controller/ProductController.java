package com.example.demoasmejb.controller;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Product;
import com.example.demoasmejb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getList(){
        return productService.getList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getDetail(@PathVariable long id){
        return productService.getDetail(id);
    }
}
