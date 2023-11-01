package com.food.ordering.system.order.service.messaging.listener;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.model.RestaurantResponseModel;
import com.food.ordering.system.order.service.domain.port.input.listener.RestaurantResponseListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantResponseKafkaListener implements KafkaConsumer<RestaurantResponseModel> {

    private static final String RECEIVED_MESSAGES = "Received {} messages: keys={}, partitions={}, offsets={}";
    private static final String PROCESSING_MESSAGE = "Processing restaurant response with status={} for order with id={}";

    private final RestaurantResponseListener restaurantResponseListener;
    private final OrderMessageMapper orderMessageMapper;

    @Override
    @KafkaListener(
            id = "${kafka-consumer-config.restaurant-consumer-group-id}",
            topics = "${order-service.restaurant-response-topic-name}")
    public void receive(
            @Payload List<RestaurantResponseModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info(RECEIVED_MESSAGES, messages.size(), keys, partitions, offsets);
        messages.forEach(this::process);
    }

    private void process(RestaurantResponseModel message) {
        log.info(PROCESSING_MESSAGE, message.getOrderApprovalStatus(), message.getOrderId());
        final var response = orderMessageMapper.toRestaurantResponse(message);
        if (message.getOrderApprovalStatus() == OrderApprovalStatus.APPROVED) {
            restaurantResponseListener.orderApproved(response);
        } else {
            restaurantResponseListener.orderRejected(response);
        }
    }
}
