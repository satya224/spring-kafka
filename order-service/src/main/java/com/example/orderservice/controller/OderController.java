package com.example.orderservice.controller;

import com.example.commonlibrary.dto.Order;
import com.example.commonlibrary.dto.OrderEvent;
import com.example.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class OderController {
    private final OrderProducer orderProducer;

    public OderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("orders")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = OrderEvent.builder()
                .status("PENDING")
                .message("order status is in pending state")
                .order(order)
                .build();
        orderProducer.sendMessage(orderEvent);
        return "Order Placed Successfully";
    }
}
