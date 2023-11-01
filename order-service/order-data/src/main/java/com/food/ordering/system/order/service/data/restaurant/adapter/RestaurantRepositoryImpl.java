package com.food.ordering.system.order.service.data.restaurant.adapter;

import com.food.ordering.system.order.service.data.restaurant.mapper.RestaurantProductDataMapper;
import com.food.ordering.system.order.service.data.restaurant.repository.RestaurantProductJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.port.output.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantProductJpaRepository restaurantProductJpaRepository;
    private final RestaurantProductDataMapper restaurantProductDataMapper;

    @Override
    public Optional<Restaurant> find(Restaurant restaurant) {
        final var entities = restaurantProductJpaRepository.findByRestaurantIdAndProductIdIn(
                restaurant.id().value(),
                restaurantProductDataMapper.toProductIds(restaurant));
        return entities.isEmpty() ? Optional.empty() : Optional.of(restaurantProductDataMapper.toRestaurant(entities));
    }
}
