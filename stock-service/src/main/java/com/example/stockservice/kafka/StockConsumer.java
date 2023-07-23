package com.example.stockservice.kafka;

import com.example.commonlibrary.dto.OrderEvent;
import com.example.stockservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockConsumer {
    private final OrderService orderService;

    public StockConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(OrderEvent orderEvent) {
        log.info(String.format("Order event received in stock service => %s", orderEvent.toString()));
        orderService.createOrder(orderEvent.getOrder());
    }

}
