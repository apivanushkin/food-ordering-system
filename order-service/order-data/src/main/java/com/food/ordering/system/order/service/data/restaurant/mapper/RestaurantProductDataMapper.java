package com.food.ordering.system.order.service.data.restaurant.mapper;

import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantProductEntity;
import com.food.ordering.system.order.service.data.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
public class RestaurantProductDataMapper {

    public List<UUID> toProductIds(Restaurant restaurant) {
        return restaurant.products().stream().map(product -> product.id().value()).toList();
    }

    public Restaurant toRestaurant(Collection<RestaurantProductEntity> entities) {
        final var restaurant = entities.stream().findFirst().orElseThrow(() ->
                new RestaurantDataAccessException("restaurant not found"));
        final var products = entities.stream()
                .map(product -> Product.builder()
                        .id(new ProductId(product.getProductId()))
                        .name(product.getProductName())
                        .price(new Money(product.getProductPrice()))
                        .build())
                .toList();

        return Restaurant.builder()
                .id(new RestaurantId(restaurant.getRestaurantId()))
                .products(products)
                .active(restaurant.getRestaurantActive())
                .build();
    }
}
