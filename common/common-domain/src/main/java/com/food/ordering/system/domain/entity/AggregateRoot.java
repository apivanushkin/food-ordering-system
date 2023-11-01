package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.value.Identity;

public abstract class AggregateRoot<ID extends Identity<?>> extends Entity<ID> {

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected AggregateRoot() {
    }
}
