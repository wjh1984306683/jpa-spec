package com.wjh.spec;

import com.wjh.spec.annotation.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

public final class Sorts {

    private Sorts() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private final List<Order> orders;

        public Builder() {
            this.orders = new ArrayList<>();
        }

        public Builder asc(String property) {
            return asc(true, property);
        }

        public <T, R> Builder asc(Getter<T, R> getter) {
            try {
                String fieldName = LambdaUtils.convertToFieldName(getter);
                return asc(true, fieldName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public <T, R> Builder desc(Getter<T, R> getter) {
            try {
                String fieldName = LambdaUtils.convertToFieldName(getter);
                return desc(true, fieldName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Builder desc(String property) {
            return desc(true, property);
        }

        public <T, R> Builder asc(boolean condition, Getter<T, R> getter) {
            if (condition) {
                orders.add(new Order(ASC, LambdaUtils.convertToFieldName(getter)));
            }
            return this;
        }

        public <T, R> Builder desc(boolean condition, Getter<T, R> getter) {
            if (condition) {
                orders.add(new Order(DESC, LambdaUtils.convertToFieldName(getter)));
            }
            return this;
        }

        public Builder asc(boolean condition, String property) {
            if (condition) {
                orders.add(new Order(ASC, property));
            }
            return this;
        }

        public Builder desc(boolean condition, String property) {
            if (condition) {
                orders.add(new Order(DESC, property));
            }
            return this;
        }

        public Sort build() {
            return Sort.by(orders);
        }
    }
}
