package com.food.ordering.system.order.service.messaging.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.BiConsumer;

@Component
@Slf4j
public class KafkaMessageHelper {

    private static final String SUCCESS_MESSAGE_TEMPLATE = "Message with key={} was sent to topic {} with offset={}";
    private static final String FAILURE_MESSAGE_TEMPLATE = "Could not send message with key={}";

    public <T> BiConsumer<SendResult<String, T>, Throwable> callback() {
        return (result, ex) -> {
            if (Objects.isNull(ex)) {
                log.info(SUCCESS_MESSAGE_TEMPLATE,
                        result.getProducerRecord().key(),
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().offset());
            } else {
                log.error(FAILURE_MESSAGE_TEMPLATE, result.getProducerRecord().key());
            }
        };
    }
}
