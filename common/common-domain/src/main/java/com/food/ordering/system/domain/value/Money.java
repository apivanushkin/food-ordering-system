package com.food.ordering.system.domain.value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal amount) {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private static BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }

    public boolean isGreaterThanZero() {
        return Objects.nonNull(amount) && amount.compareTo(ZERO.amount) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return Objects.nonNull(amount) && amount.compareTo(money.amount) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(amount.add(money.amount)));
    }

    public Money subtract(Money money) {
        return new Money(setScale(amount.subtract(money.amount)));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(amount.multiply(new BigDecimal(multiplier))));
    }
}
