package com.ejerciciointegrador.order_service.service;

import com.ejerciciointegrador.order_service.dto.OrderDTO;
import com.ejerciciointegrador.order_service.dto.ProductDTO;
import com.ejerciciointegrador.order_service.enums.StateEnum;
import com.ejerciciointegrador.order_service.model.Order;
import com.ejerciciointegrador.order_service.model.OrderItem;
import com.ejerciciointegrador.order_service.repository.IOrderRepository;
import com.ejerciciointegrador.order_service.repository.IProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductClient productClient;


    @CircuitBreaker(name = "product-service", fallbackMethod = "createOrderFallback")
    @Retry(name = "product-service")
    @Override
    public Order CreateOrder(List<OrderDTO> products) {
        //Crear Orden, definir dia y estado
        Order order=new Order();
        order.setDate(LocalDate.now());
        order.setState(StateEnum.CREADO);
        //Buscar nombre y precio de los productos seleccionados
        List<ProductDTO> productDTOS=productClient.findAllProductsByIds(products.stream()
                .map(OrderDTO::getId)
                .toList());
        //Obtener detalle de la orden
        List<OrderItem> orderItems = getItems(products, productDTOS);
        order.setItems(orderItems);
        //Guardar orden en la BD
        orderRepository.save(order);
        return order;
    }

    public Order createOrderFallback(List<OrderDTO> products, Throwable ex) {
        throw new IllegalStateException("Product-service no disponible. No se pudo crear la orden.", ex);
    }

    private List<OrderItem> getItems(List<OrderDTO> products, List<ProductDTO> productDTOS) {
        List<OrderItem> orderItems=new ArrayList<>();
        int i=0;
        for (OrderDTO p: products){
            OrderItem orderItem=new OrderItem();
            orderItem.setProductId(p.getId());
            orderItem.setProductName(productDTOS.get(i).getName());
            orderItem.setQuantity(p.getQuantity());
            orderItem.setUnitPrice(productDTOS.get(i).getPrice());
            orderItem.setSubtotal(orderItem.getUnitPrice()*orderItem.getQuantity());
            orderItems.add(orderItem);
            i++;
        }
        return orderItems;
    }


    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order=orderRepository.findById(orderId).orElse(null);
        if (order!=null) {
            if (order.getState() != StateEnum.CONFIRMADO) {
                order.setState(StateEnum.CANCELADO);
                orderRepository.save(order);
            }
            else
                return "Pedido confirmado. Solicitud denegada";
        }
        else
            return "El pedido no está registrado";
        return "Pedido cancelado con éxito";
    }
}
