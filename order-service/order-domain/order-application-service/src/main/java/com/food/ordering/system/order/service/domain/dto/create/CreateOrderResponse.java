package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.domain.value.OrderStatus;

import java.util.UUID;

public record CreateOrderResponse(UUID trackingId, OrderStatus orderStatus, String message) {
}
