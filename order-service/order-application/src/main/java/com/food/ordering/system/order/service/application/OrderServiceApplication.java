package com.food.ordering.system.order.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.food.ordering.system")
@EntityScan(basePackages = {"com.food.ordering.system.data", "com.food.ordering.system.order.service.data"})
@EnableJpaRepositories(basePackages = {"com.food.ordering.system.data", "com.food.ordering.system.order.service.data"})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
