package com.food.ordering.system.order.service.domain.exception;

public class OrderNotFoundException extends OrderDomainException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
