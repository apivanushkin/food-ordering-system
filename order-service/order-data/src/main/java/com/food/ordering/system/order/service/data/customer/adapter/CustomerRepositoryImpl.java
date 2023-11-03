package com.food.ordering.system.order.service.data.customer.adapter;

import com.food.ordering.system.order.service.data.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.order.service.domain.port.output.repository.CustomerRepository;
import com.food.ordering.system.domain.value.CustomerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public boolean existsById(CustomerId id) {
        return customerJpaRepository.existsById(id.value());
    }
}
