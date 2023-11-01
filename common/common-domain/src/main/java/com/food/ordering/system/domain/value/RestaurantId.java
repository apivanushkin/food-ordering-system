package com.food.ordering.system.domain.value;

import java.util.UUID;

public class RestaurantId extends Identity<UUID> {

    public RestaurantId(UUID value) {
        super(value);
    }
}
