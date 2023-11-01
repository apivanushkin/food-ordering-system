package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.domain.value.StreetAddress;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.value.CustomerId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public Order toOrder(CreateOrderCommand command) {
        return Order.builder()
                .customerId(new CustomerId(command.customerId()))
                .restaurantId(new RestaurantId(command.restaurantId()))
                .deliveryAddress(new StreetAddress(command.address().street(), command.address().city()))
                .price(new Money(command.price()))
                .items(toOrderItems(command.items()))
                .build();
    }

    public Restaurant toRestaurant(CreateOrderCommand command) {
        return Restaurant.builder()
                .id(new RestaurantId(command.restaurantId()))
                .products(command.items().stream().map(item -> new Product(new ProductId(item.productId()))).toList())
                .build();
    }

    public CreateOrderResponse toCreateOrderResponse(Order order, String message) {
        return new CreateOrderResponse(order.trackingId().value(), order.status(), message);
    }

    public TrackOrderResponse toTrackOrderResponse(Order order) {
        return new TrackOrderResponse(order.trackingId().value(), order.status());
    }

    private List<OrderItem> toOrderItems(List<com.food.ordering.system.order.service.domain.dto.OrderItem> items) {
        return items.stream()
                .map(item -> OrderItem.builder()
                        .product(new Product(new ProductId(item.productId())))
                        .price(new Money(item.price()))
                        .quantity(item.quantity())
                        .subTotal(new Money(item.subTotal()))
                        .build())
                .toList();
    }
}
