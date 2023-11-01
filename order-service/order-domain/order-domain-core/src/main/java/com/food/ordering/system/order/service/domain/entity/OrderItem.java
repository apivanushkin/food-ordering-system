package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.Entity;
import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.domain.value.OrderItemId;

import java.util.Objects;

public final class OrderItem extends Entity<OrderItemId> {

    private final Product product;
    private final Money price;
    private final Integer quantity;
    private final Money subTotal;
    private OrderId orderId;

    private OrderItem(Builder builder) {
        id = builder.id;
        product = builder.product;
        price = builder.price;
        quantity = builder.quantity;
        subTotal = builder.subTotal;
        orderId = builder.orderId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Product product() {
        return product;
    }

    public Money price() {
        return price;
    }

    public Integer quantity() {
        return quantity;
    }

    public Money subTotal() {
        return subTotal;
    }

    public OrderId orderId() {
        return orderId;
    }

    public boolean hasValidPrice() {
        return Objects.nonNull(price) &&
                price.isGreaterThanZero() &&
                price.equals(product.price()) &&
                price.multiply(quantity).equals(subTotal);
    }

    void initialize(OrderItemId orderItemId, OrderId orderId) {
        id = orderItemId;
        this.orderId = orderId;
    }

    public static final class Builder {
        private OrderItemId id;
        private Product product;
        private Money price;
        private Integer quantity;
        private Money subTotal;
        private OrderId orderId;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
