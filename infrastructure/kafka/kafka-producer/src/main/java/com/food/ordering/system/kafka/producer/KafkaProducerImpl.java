package com.food.ordering.system.kafka.producer;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiConsumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        log.info("Sending message with key={} to topic {}", key, topicName);
        try {
            kafkaTemplate.send(topicName, key, message).whenComplete(callback);
        } catch (KafkaException e) {
            log.error("An error occurred during sending message with key={} to topic {}", key, topicName);
            throw new KafkaProducerException("message sending failed");
        }
    }

    @PreDestroy
    @SuppressWarnings("unused")
    public void close() {
        if (Objects.nonNull(kafkaTemplate)) {
            log.info("Closing gracefully");
            kafkaTemplate.destroy();
        }
    }
}
