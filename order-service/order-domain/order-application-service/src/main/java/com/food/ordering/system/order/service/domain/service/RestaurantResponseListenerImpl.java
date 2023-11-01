package com.food.ordering.system.order.service.domain.service;

import com.food.ordering.system.order.service.domain.dto.RestaurantResponse;
import com.food.ordering.system.order.service.domain.port.input.listener.RestaurantResponseListener;
import org.springframework.stereotype.Service;

@Service
public class RestaurantResponseListenerImpl implements RestaurantResponseListener {

    @Override
    public void orderApproved(RestaurantResponse restaurantResponse) {

    }

    @Override
    public void orderRejected(RestaurantResponse restaurantResponse) {

    }
}
