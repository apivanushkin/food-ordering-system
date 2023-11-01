package com.food.ordering.system.order.service.domain.port.input.listener;

import com.food.ordering.system.order.service.domain.dto.RestaurantResponse;
import jakarta.validation.Valid;

public interface RestaurantResponseListener {

    void orderApproved(@Valid RestaurantResponse restaurantResponse);

    void orderRejected(@Valid RestaurantResponse restaurantResponse);
}
