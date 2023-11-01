package com.food.ordering.system.domain.value;

import java.util.UUID;

public class ProductId extends Identity<UUID> {

    public ProductId(UUID value) {
        super(value);
    }
}
