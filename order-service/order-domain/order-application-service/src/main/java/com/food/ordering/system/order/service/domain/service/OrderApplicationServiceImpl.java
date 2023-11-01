package com.food.ordering.system.order.service.domain.service;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.handler.CreateOrderCommandHandler;
import com.food.ordering.system.order.service.domain.handler.TrackOrderQueryHandler;
import com.food.ordering.system.order.service.domain.port.input.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final CreateOrderCommandHandler createOrderCommandHandler;
    private final TrackOrderQueryHandler trackOrderQueryHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        log.debug(command.toString());
        return createOrderCommandHandler.handle(command);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        log.debug(query.toString());
        return trackOrderQueryHandler.handle(query);
    }
}
