package com.food.ordering.system.order.service.domain.port.output.repository;

import com.food.ordering.system.domain.value.CustomerId;

public interface CustomerRepository {

    boolean existsById(CustomerId id);
}
