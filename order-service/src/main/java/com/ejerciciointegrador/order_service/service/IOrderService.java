package com.ejerciciointegrador.order_service.service;

import com.ejerciciointegrador.order_service.dto.OrderDTO;
import com.ejerciciointegrador.order_service.dto.ProductDTO;
import com.ejerciciointegrador.order_service.enums.StateEnum;
import com.ejerciciointegrador.order_service.model.Order;
import com.ejerciciointegrador.order_service.model.OrderItem;

import java.util.List;

public interface IOrderService {
    public Order CreateOrder(List<OrderDTO> products);
    public Order findOrderById(Long id);
    public String cancelOrder(Long orderId);
}
