package com.example.Products.service.impl;

import com.example.Products.exception.ProductNotFoundException;
import com.example.Products.model.Product;
import com.example.Products.repository.ProductRepository;
import com.example.Products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product){

        return productRepository.save(product);

    }

    @Override
    public void deleteById(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public Product update(Long id, Product product){

        product.setId(id);
        return create(product);

    }

    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

}
