package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.Entity;
import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;

public final class Product extends Entity<ProductId> {

    private String name;
    private Money price;

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        price = builder.price;
    }

    public Product(ProductId id) {
        super(id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }

    void updateNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
