package com.ejerciciointegrador.product_service.service;

import com.ejerciciointegrador.product_service.model.Product;

import java.util.List;

public interface IProductService {
    public Product createProduct(Product product);
    public Product findProductById(Long id);
    public List<Product> findAllProductsByIds(List<Long> idList);
}
