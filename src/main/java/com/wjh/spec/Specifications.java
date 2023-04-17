package com.wjh.spec;


import static jakarta.persistence.criteria.Predicate.BooleanOperator.AND;
import static jakarta.persistence.criteria.Predicate.BooleanOperator.OR;

public class Specifications {
    public static <T> PredicateBuilder<T> and() {
        return new PredicateBuilder<>(AND);
    }

    public static <T> PredicateBuilder<T> or() {
        return new PredicateBuilder<>(OR);
    }
}
