package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public abstract sealed class OrderEvent implements DomainEvent<Order>
        permits OrderCanceledEvent, OrderCreatedEvent, OrderPaidEvent {

    protected final Order order;
    protected final ZonedDateTime createdAt;

    protected OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public Order order() {
        return order;
    }

    public ZonedDateTime createdAt() {
        return createdAt;
    }
}
