package com.food.ordering.system.order.service.domain.value;

import com.food.ordering.system.domain.value.Identity;

import java.util.UUID;

public class CustomerId extends Identity<UUID> {

    public CustomerId(UUID value) {
        super(value);
    }
}
