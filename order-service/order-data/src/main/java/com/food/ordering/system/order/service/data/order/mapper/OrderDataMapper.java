package com.food.ordering.system.order.service.data.order.mapper;

import com.food.ordering.system.domain.value.*;
import com.food.ordering.system.order.service.data.order.entity.OrderEntity;
import com.food.ordering.system.order.service.data.order.entity.OrderItemEntity;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.value.CustomerId;
import com.food.ordering.system.order.service.domain.value.OrderItemId;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDataMapper {

    private static final String DELIMITER = ";";
    private static final String EMPTY = "";

    public OrderEntity toOrderEntity(Order order) {
        final var orderEntity = OrderEntity.builder()
                .id(order.id().value())
                .customerId(order.customerId().value())
                .restaurantId(order.restaurantId().value())
                .street(order.deliveryAddress().street())
                .city(order.deliveryAddress().city())
                .price(order.price().amount())
                .items(toOrderItemEntities(order.items()))
                .trackingId(order.trackingId().value())
                .status(order.status())
                .failureMessages(Optional.ofNullable(order.failureMessages())
                        .map(failureMessages -> String.join(DELIMITER, failureMessages))
                        .orElse(EMPTY))
                .build();
        orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));
        return orderEntity;
    }

    public Order toOrder(OrderEntity entity) {
        return Order.builder()
                .id(new OrderId(entity.getId()))
                .customerId(new CustomerId(entity.getCustomerId()))
                .restaurantId(new RestaurantId(entity.getRestaurantId()))
                .deliveryAddress(new StreetAddress(entity.getStreet(), entity.getCity()))
                .price(new Money(entity.getPrice()))
                .items(toOrderItems(entity.getItems()))
                .trackingId(new TrackingId(entity.getTrackingId()))
                .status(entity.getStatus())
                .failureMessages(entity.getFailureMessages().isEmpty()
                        ? new ArrayList<>()
                        : Arrays.stream(entity.getFailureMessages().split(DELIMITER)).toList())
                .build();
    }

    private List<OrderItemEntity> toOrderItemEntities(List<OrderItem> items) {
        return Objects.isNull(items) ? null : items.stream()
                .map(item -> OrderItemEntity.builder()
                        .id(item.id().value())
                        .productId(item.product().id().value())
                        .price(item.price().amount())
                        .quantity(item.quantity())
                        .subTotal(item.subTotal().amount())
                        .build())
                .toList();
    }

    private List<OrderItem> toOrderItems(List<OrderItemEntity> entities) {
        return Objects.isNull(entities) ? null : entities.stream()
                .map(entity -> OrderItem.builder()
                        .id(new OrderItemId(entity.getId()))
                        .product(new Product(new ProductId(entity.getProductId())))
                        .price(new Money(entity.getPrice()))
                        .quantity(entity.getQuantity())
                        .subTotal(new Money(entity.getSubTotal()))
                        .orderId(new OrderId(entity.getOrder().getId()))
                        .build())
                .toList();
    }
}
