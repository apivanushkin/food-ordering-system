package com.food.ordering.system.order.service.messaging.adapter;

import com.food.ordering.system.kafka.model.RestaurantApprovalRequest;
import com.food.ordering.system.kafka.producer.KafkaProducer;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;
import com.food.ordering.system.order.service.domain.port.output.publisher.OrderPaidEventPublisher;
import com.food.ordering.system.order.service.messaging.config.OrderMessageConfigData;
import com.food.ordering.system.order.service.messaging.helper.KafkaMessageHelper;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RestaurantApprovalRequestPublisher implements OrderPaidEventPublisher {

    private final OrderMessageConfigData configData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final KafkaProducer<String, RestaurantApprovalRequest> kafkaProducer;
    private final OrderMessageMapper orderMessageMapper;

    @Override
    public void publish(OrderPaidEvent event) {
        kafkaProducer.send(
                configData.getRestaurantApprovalRequestTopicName(),
                event.order().id().toString(),
                orderMessageMapper.toRestaurantApprovalRequest(event),
                kafkaMessageHelper.callback());
    }
}
