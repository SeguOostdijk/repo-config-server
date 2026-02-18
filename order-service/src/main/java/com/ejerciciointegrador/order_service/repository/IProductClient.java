package com.ejerciciointegrador.order_service.repository;

import com.ejerciciointegrador.order_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service")
public interface IProductClient {
    @GetMapping("/products")
    public List<ProductDTO> findAllProductsByIds(@RequestParam List<Long> ids);
}
