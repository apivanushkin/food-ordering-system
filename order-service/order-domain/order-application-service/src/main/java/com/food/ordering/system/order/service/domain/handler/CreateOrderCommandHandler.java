package com.food.ordering.system.order.service.domain.handler;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.helper.CreateOrderHelper;
import com.food.ordering.system.order.service.domain.mapper.OrderMapper;
import com.food.ordering.system.order.service.domain.port.output.publisher.OrderCreatedEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderCommandHandler {

    private static final String ORDER_CREATED = "order created";

    private final OrderCreatedEventPublisher orderCreatedEventPublisher;
    private final CreateOrderHelper createOrderHelper;
    private final OrderMapper orderMapper;

    @Transactional
    public CreateOrderResponse handle(CreateOrderCommand command) {
        final var event = createOrderHelper.persistOrder(command);
        log.info("Order created with id={}", event.order().id());
        orderCreatedEventPublisher.publish(event);
        return orderMapper.toCreateOrderResponse(event.order(), ORDER_CREATED);
    }
}
