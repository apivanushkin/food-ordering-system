package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.value.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.order.service.domain.value.OrderItemId;
import com.food.ordering.system.order.service.domain.value.TrackingId;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money price;
    private final List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus status;
    private List<String> failureMessages;

    private Order(Builder builder) {
        id = builder.id;
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        status = builder.status;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerId customerId() {
        return customerId;
    }

    public RestaurantId restaurantId() {
        return restaurantId;
    }

    public StreetAddress deliveryAddress() {
        return deliveryAddress;
    }

    public Money price() {
        return price;
    }

    public List<OrderItem> items() {
        return Objects.nonNull(items) ? List.copyOf(items) : null;
    }

    public TrackingId trackingId() {
        return trackingId;
    }

    public OrderStatus status() {
        return status;
    }

    public List<String> failureMessages() {
        return Objects.nonNull(failureMessages) ? List.copyOf(failureMessages) : null;
    }

    public void validate() {
        validateInitialState();
        validatePrice();
        validateItemsPrice();
    }

    public void initialize() {
        id = new OrderId(UUID.randomUUID());
        trackingId = new TrackingId(UUID.randomUUID());
        status = OrderStatus.PENDING;
        initializeItems();
    }

    public void updateProductsNameAndPrice(Collection<Product> source) {
        final var products = source.stream().collect(Collectors.toMap(Product::id, Function.identity()));
        items.forEach(item ->
                Optional.ofNullable(products.get(item.product().id())).ifPresent(product ->
                        item.product().updateNameAndPrice(product.name(), product.price())));
    }

    public void pay() {
        if (status == OrderStatus.PENDING) {
            status = OrderStatus.PAID;
        } else {
            throw new OrderDomainException("order status is invalid for pay");
        }
    }

    public void approve() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.APPROVED;
        } else {
            throw new OrderDomainException("order status is invalid for approve");
        }
    }

    public void initCancel(List<String> failureMessages) {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.CANCELING;
            updateFailureMessages(failureMessages);
        } else {
            throw new OrderDomainException("order status is invalid for init cancel");
        }
    }

    public void cancel(List<String> failureMessages) {
        if (status == OrderStatus.PENDING || status == OrderStatus.CANCELING) {
            status = OrderStatus.CANCELED;
            updateFailureMessages(failureMessages);
        } else {
            throw new OrderDomainException("order status is invalid for cancel");
        }
    }

    private void validateInitialState() {
        if (Objects.nonNull(id) || Objects.nonNull(status)) {
            throw new OrderDomainException("order initial state is invalid");
        }
    }

    private void validatePrice() {
        if (Objects.isNull(price) || !price.isGreaterThanZero()) {
            throw new OrderDomainException("order price must be greater than zero");
        }
    }

    private void validateItemsPrice() {
        final var total = items.stream()
                .peek(this::validateItemPrice)
                .map(OrderItem::subTotal)
                .reduce(Money.ZERO, Money::add);
        if (!price.equals(total)) {
            throw new OrderDomainException("order price must be equal to items total");
        }
    }

    private void validateItemPrice(OrderItem item) {
        if (!item.hasValidPrice()) {
            throw new OrderDomainException("price is invalid for product with id: " + item.product().id());
        }
    }

    private void initializeItems() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).initialize(new OrderItemId(i + 1), id);
        }
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (Objects.nonNull(this.failureMessages) && Objects.nonNull(failureMessages)) {
            this.failureMessages.addAll(failureMessages.stream().filter(msg -> !msg.isEmpty()).toList());
        }
        if (Objects.isNull(this.failureMessages)) {
            this.failureMessages = failureMessages;
        }
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus status;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder status(OrderStatus val) {
            status = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
