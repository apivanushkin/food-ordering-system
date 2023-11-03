package com.food.ordering.system.order.service.messaging.adapter;

import com.food.ordering.system.kafka.model.PaymentRequestModel;
import com.food.ordering.system.kafka.producer.KafkaProducer;
import com.food.ordering.system.order.service.domain.event.OrderCanceledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.port.output.publisher.OrderCanceledEventPublisher;
import com.food.ordering.system.order.service.domain.port.output.publisher.OrderCreatedEventPublisher;
import com.food.ordering.system.order.service.messaging.config.OrderMessageConfigData;
import com.food.ordering.system.order.service.messaging.helper.KafkaMessageHelper;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentRequestPublisher implements OrderCreatedEventPublisher, OrderCanceledEventPublisher {

    private final OrderMessageConfigData orderMessageConfigData;
    private final KafkaMessageHelper kafkaMessageHelper;
    private final KafkaProducer<String, PaymentRequestModel> kafkaProducer;
    private final OrderMessageMapper orderMessageMapper;

    @Override
    public void publish(OrderCreatedEvent event) {
        kafkaProducer.send(
                orderMessageConfigData.getPaymentRequestTopic(),
                event.order().id().toString(),
                orderMessageMapper.toPaymentRequest(event),
                kafkaMessageHelper.callback());
    }

    @Override
    public void publish(OrderCanceledEvent event) {
        kafkaProducer.send(
                orderMessageConfigData.getPaymentRequestTopic(),
                event.order().id().toString(),
                orderMessageMapper.toPaymentRequest(event),
                kafkaMessageHelper.callback());
    }
}
