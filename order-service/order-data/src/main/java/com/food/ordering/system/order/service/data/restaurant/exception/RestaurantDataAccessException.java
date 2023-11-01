package com.food.ordering.system.order.service.data.restaurant.exception;

public final class RestaurantDataAccessException extends RuntimeException {

    public RestaurantDataAccessException(String message) {
        super(message);
    }

    public RestaurantDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
