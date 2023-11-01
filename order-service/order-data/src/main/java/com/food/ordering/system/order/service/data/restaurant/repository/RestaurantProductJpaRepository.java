package com.food.ordering.system.order.service.data.restaurant.repository;

import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantProductEntity;
import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantProductEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantProductJpaRepository extends JpaRepository<RestaurantProductEntity, RestaurantProductEntityId> {

    List<RestaurantProductEntity> findByRestaurantIdAndProductIdIn(UUID restaurantId, List<UUID> productIds);
}
