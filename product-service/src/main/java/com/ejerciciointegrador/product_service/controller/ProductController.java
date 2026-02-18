package com.ejerciciointegrador.product_service.controller;

import com.ejerciciointegrador.product_service.model.Product;
import com.ejerciciointegrador.product_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProductsByIds(@RequestParam List<Long> ids){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.findAllProductsByIds(ids));
    }
}
