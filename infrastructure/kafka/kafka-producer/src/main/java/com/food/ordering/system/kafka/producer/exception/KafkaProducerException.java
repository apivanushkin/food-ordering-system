package com.food.ordering.system.kafka.producer.exception;

public final class KafkaProducerException extends RuntimeException {

    public KafkaProducerException(String message) {
        super(message);
    }
}
