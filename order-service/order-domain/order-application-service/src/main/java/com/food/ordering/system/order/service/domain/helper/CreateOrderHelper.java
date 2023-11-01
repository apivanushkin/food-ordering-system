package com.food.ordering.system.order.service.domain.helper;

import com.food.ordering.system.order.service.domain.OrderDomainService;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.mapper.OrderMapper;
import com.food.ordering.system.order.service.domain.port.output.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.port.output.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.port.output.repository.RestaurantRepository;
import com.food.ordering.system.order.service.domain.value.CustomerId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderHelper {

    private final OrderDomainService orderDomainService;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public OrderCreatedEvent persistOrder(CreateOrderCommand command) {
        checkCustomer(command.customerId());
        final var restaurant = checkRestaurant(command);
        final var order = orderMapper.toOrder(command);
        final var event = orderDomainService.validateAndInitiateOrder(order, restaurant);
        orderRepository.save(order);
        return event;
    }

    private void checkCustomer(UUID customerId) {
        if (!customerRepository.existsById(new CustomerId(customerId))) {
            log.warn("Could not find customer with id={}", customerId);
            throw new OrderDomainException("customer doesn't exit");
        }
    }

    private Restaurant checkRestaurant(CreateOrderCommand command) {
        return restaurantRepository.find(orderMapper.toRestaurant(command)).orElseThrow(() -> {
            log.warn("Could not find restaurant with id={}", command.restaurantId());
            return new OrderDomainException("restaurant doesn't exist");
        });
    }
}
