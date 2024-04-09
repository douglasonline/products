package com.example.Products.controllers;

import com.example.Products.exception.ProductNotFoundException;
import com.example.Products.model.Product;
import com.example.Products.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    Logger LOGGER = LoggerFactory.getLogger(ProductController.class);


    @GetMapping
    public ResponseEntity getAll(){

        List<Product> all = productService.getAll();
        LOGGER.info("OBTER TUDO: " + all);
        return ResponseEntity.ok(all);

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        try {
            Product product = productService.getById(id);
            LOGGER.info("OBTER POR ID: " + id);
            return ResponseEntity.ok(product);

        } catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Mensagem", "Produto não encontrado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try {
            Product product = productService.getById(id);
            productService.deleteById(product.getId());
            LOGGER.info("EXCLUIR: " + id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("Mensagem", "Produto com o id " + id + " excluido com sucesso!"));

        } catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Mensagem", "Produto não encontrado"));
        }

    }

    @PostMapping
    public ResponseEntity create(@RequestBody Product product){

        Product product1 = productService.create(product);
        LOGGER.info("CRIAR: " + product1);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Product product){

        try {

            Product product1 = productService.getById(id);
            Product updated = productService.update(product1.getId(), product);
            LOGGER.info("ATUALIZAR: " + updated);
            return ResponseEntity.ok(updated);

        } catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Mensagem", "Produto não encontrado"));
        }

    }

}
