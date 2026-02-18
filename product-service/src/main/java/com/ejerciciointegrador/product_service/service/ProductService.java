package com.ejerciciointegrador.product_service.service;

import com.ejerciciointegrador.product_service.model.Product;
import com.ejerciciointegrador.product_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAllProductsByIds(List<Long> idList) {
        return productRepository.findAllById(idList);
    }
}
