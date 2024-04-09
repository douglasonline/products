package com.example.Products.service;

import com.example.Products.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public abstract List<Product> getAll();

    public abstract Product create(Product produto);

    public abstract void deleteById(Long id);

    public abstract Product update(Long id, Product product);

    public abstract Product getById(Long id);

}
