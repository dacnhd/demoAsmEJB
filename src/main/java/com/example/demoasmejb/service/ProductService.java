package com.example.demoasmejb.service;

import com.example.demoasmejb.entity.Product;
import com.example.demoasmejb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getList(){
        return productRepository.findAll();
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product getDetail(long id){
        if (productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        return null;
    }

    public Product update(long id, Product product){
        if (productRepository.findById(id).isPresent()){
            Product product1 = productRepository.findById(id).get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            return productRepository.save(product1);
        }
        return null;
    }
}
