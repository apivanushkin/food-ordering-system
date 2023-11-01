package com.food.ordering.system.order.service.domain.port.output.repository;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.value.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(OrderId id);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
