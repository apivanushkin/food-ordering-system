package com.food.ordering.system.order.service.domain.port.output.publisher;

import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedEventPublisher {

    void publish(OrderCreatedEvent event);
}
