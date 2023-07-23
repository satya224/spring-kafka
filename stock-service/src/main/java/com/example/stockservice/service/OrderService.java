package com.example.stockservice.service;

import com.example.commonlibrary.dto.Order;
import com.example.stockservice.entity.OrderEntity;
import com.example.stockservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void createOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getOrderId());
        orderEntity.setName(order.getName());
        orderEntity.setPrice(order.getPrice());
        orderEntity.setQuantity(order.getQuantity());
        orderRepository.save(orderEntity);
        log.info(String.format("Order saved to db => %s", order));

    }
}
