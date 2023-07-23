package com.example.emailservice.kafka;

import com.example.commonlibrary.dto.OrderEvent;
import com.example.emailservice.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailConsumer {

    private final EmailSenderService emailSenderService;

    public EmailConsumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(OrderEvent orderEvent) {
        log.info(String.format("Order event received in email service => %s", orderEvent.toString()));
        emailSenderService.sendEmail(
                "satyaprakash101010@gmail.com",
                "Order with order id "+orderEvent.getOrder().getOrderId()+"is created successfully",
                "Spring Boot Mail");
    }

}
