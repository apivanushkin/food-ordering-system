package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.order.service.domain.dto.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.OrderItem;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
        @NotNull UUID customerId,
        @NotNull UUID restaurantId,
        @NotNull OrderAddress address,
        @NotNull BigDecimal price,
        @NotNull List<OrderItem> items) {
}
