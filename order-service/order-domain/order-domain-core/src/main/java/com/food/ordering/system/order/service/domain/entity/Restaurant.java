package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.value.RestaurantId;

import java.util.List;
import java.util.Objects;

public class Restaurant extends AggregateRoot<RestaurantId> {

    private final Boolean active;
    private final List<Product> products;

    private Restaurant(Builder builder) {
        id = builder.id;
        active = builder.active;
        products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Boolean active() {
        return active;
    }

    public List<Product> products() {
        return Objects.nonNull(products) ? List.copyOf(products) : null;
    }

    public static final class Builder {
        private RestaurantId id;
        private Boolean active;
        private List<Product> products;

        private Builder() {
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder active(Boolean val) {
            active = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
