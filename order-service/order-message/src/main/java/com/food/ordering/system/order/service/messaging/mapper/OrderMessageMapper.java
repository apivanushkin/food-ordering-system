package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.kafka.model.*;
import com.food.ordering.system.order.service.domain.dto.PaymentResponse;
import com.food.ordering.system.order.service.domain.dto.RestaurantResponse;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.event.OrderCanceledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OrderMessageMapper {

    public PaymentRequestModel toPaymentRequest(OrderCreatedEvent event) {
        return PaymentRequestModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(event.order().customerId().value().toString())
                .setOrderId(event.order().id().value().toString())
                .setPrice(event.order().price().amount())
                .setCreatedAt(event.createdAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public PaymentRequestModel toPaymentRequest(OrderCanceledEvent event) {
        return PaymentRequestModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(event.order().id().value().toString())
                .setCustomerId(event.order().customerId().value().toString())
                .setPrice(event.order().price().amount())
                .setCreatedAt(event.createdAt().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                .build();
    }

    public RestaurantRequestModel toRestaurantApprovalRequest(OrderPaidEvent event) {
        return RestaurantRequestModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(event.order().id().value().toString())
                .setRestaurantId(event.order().restaurantId().value().toString())
                .setProducts(toProducts(event.order().items()))
                .setPrice(event.order().price().amount())
                .setCreatedAt(event.createdAt().toInstant())
                .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
                .build();
    }

    public PaymentResponse toPaymentReponse(PaymentResponseModel model) {
        return null;
    }

    public RestaurantResponse toRestaurantResponse(RestaurantResponseModel model) {
        return null;
    }

    private List<Product> toProducts(List<OrderItem> items) {
        return Objects.isNull(items) ? null : items.stream()
                .map(item -> Product.newBuilder()
                        .setId(item.product().id().toString())
                        .setQuantity(item.quantity())
                        .build())
                .toList();
    }
}
