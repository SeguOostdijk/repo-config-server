package com.ejerciciointegrador.order_service.controller;

import com.ejerciciointegrador.order_service.dto.OrderDTO;
import com.ejerciciointegrador.order_service.model.Order;
import com.ejerciciointegrador.order_service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody List<OrderDTO> orderDTOs){
        return orderService.CreateOrder(orderDTOs);
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable Long id){
        return orderService.findOrderById(id);
    }

    @PatchMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id){
        return orderService.cancelOrder(id);
    }
}
