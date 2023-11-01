package com.food.ordering.system.order.service.domain.value;

import com.food.ordering.system.domain.value.Identity;

import java.util.UUID;

public final class TrackingId extends Identity<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
