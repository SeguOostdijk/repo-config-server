package com.ejerciciointegrador.order_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Embeddable
public class OrderItem {
    private Long productId;
    private Long quantity;
    private String productName;
    private Double unitPrice;
    private Double subtotal;

}
