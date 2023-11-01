package com.food.ordering.system.order.service.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItem(
        @NotNull UUID productId,
        @NotNull BigDecimal price,
        @NotNull Integer quantity,
        @NotNull BigDecimal subTotal) {
}
