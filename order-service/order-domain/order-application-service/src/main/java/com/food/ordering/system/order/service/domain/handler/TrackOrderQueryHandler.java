package com.food.ordering.system.order.service.domain.handler;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderMapper;
import com.food.ordering.system.order.service.domain.port.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackOrderQueryHandler {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse handle(TrackOrderQuery query) {
        return orderRepository.findByTrackingId(new TrackingId(query.trackingId()))
                .map(orderMapper::toTrackOrderResponse)
                .orElseThrow(() -> {
                    log.warn("Could not find order with tracking_id={}", query.trackingId());
                    return new OrderNotFoundException("order doesn't exist");
                });
    }
}
