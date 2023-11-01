package com.food.ordering.system.order.service.domain.dto.track;

import com.food.ordering.system.domain.value.OrderStatus;

import java.util.UUID;

public record TrackOrderResponse(UUID trackingId, OrderStatus orderStatus) {
}
