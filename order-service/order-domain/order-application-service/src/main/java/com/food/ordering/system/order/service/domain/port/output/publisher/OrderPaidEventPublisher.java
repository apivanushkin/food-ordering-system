package com.food.ordering.system.order.service.domain.port.output.publisher;

import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidEventPublisher {

    void publish(OrderPaidEvent event);
}
