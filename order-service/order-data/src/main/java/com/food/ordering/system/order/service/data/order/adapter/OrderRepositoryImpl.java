package com.food.ordering.system.order.service.data.order.adapter;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.data.order.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.data.order.repository.OrderJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.port.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDataMapper orderDataMapper;
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(Order order) {
        orderJpaRepository.save(orderDataMapper.toOrderEntity(order));
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderJpaRepository.findById(id.value()).map(orderDataMapper::toOrder);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return Optional.empty();
    }
}
