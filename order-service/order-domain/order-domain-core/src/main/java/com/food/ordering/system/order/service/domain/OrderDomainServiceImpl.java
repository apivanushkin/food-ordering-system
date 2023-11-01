package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.value.DomainConstant;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.event.OrderCanceledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        order.updateProductsNameAndPrice(restaurant.products());
        order.validate();
        order.initialize();
        log.info("Order with id={} validated and initiated", order.id());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(DomainConstant.UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id={} paid", order.id());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(DomainConstant.UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id={} approved", order.id());
    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Payment for order with id={} canceled", order.id());
        return new OrderCanceledEvent(order, ZonedDateTime.now(ZoneId.of(DomainConstant.UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order with id={} canceled", order.id());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.active()) {
            throw new OrderDomainException("restaurant must be active");
        }
    }
}
