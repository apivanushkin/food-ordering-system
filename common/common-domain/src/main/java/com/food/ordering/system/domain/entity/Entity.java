package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.value.Identity;

import java.util.Objects;

public abstract class Entity<ID extends Identity<?>> {

    protected ID id;

    protected Entity(ID id) {
        this.id = id;
    }

    protected Entity() {
    }

    public ID id() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + id + ']';
    }
}
