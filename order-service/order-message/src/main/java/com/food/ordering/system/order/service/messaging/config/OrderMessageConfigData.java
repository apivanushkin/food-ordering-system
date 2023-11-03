package com.food.ordering.system.order.service.messaging.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service")
public class OrderMessageConfigData {

    private String paymentRequestTopic;
    private String paymentResponseTopic;
    private String restaurantApprovalRequestTopic;
    private String restaurantApprovalResponseTopic;
}
