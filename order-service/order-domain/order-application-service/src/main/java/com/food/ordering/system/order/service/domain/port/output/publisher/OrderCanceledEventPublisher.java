package com.food.ordering.system.order.service.domain.port.output.publisher;

import com.food.ordering.system.order.service.domain.event.OrderCanceledEvent;

public interface OrderCanceledEventPublisher {

    void publish(OrderCanceledEvent event);
}
