package com.food.ordering.system.order.service.domain.dto;

import jakarta.validation.constraints.NotNull;

public record OrderAddress(@NotNull String street, @NotNull String city) {
}
