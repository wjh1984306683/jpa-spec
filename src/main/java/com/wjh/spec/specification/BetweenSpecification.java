package com.wjh.spec.specification;


import com.wjh.spec.LambdaUtils;
import com.wjh.spec.annotation.Getter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class BetweenSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final Comparable<Object> lower;
    private final Comparable<Object> upper;

    public BetweenSpecification(String property, Object lower, Object upper) {
        this.property = property;
        this.lower = (Comparable<Object>) lower;
        this.upper = (Comparable<Object>) upper;
    }

    public BetweenSpecification(Getter<T, ?> getter, Object lower, Object upper) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.lower = (Comparable<Object>) lower;
        this.upper = (Comparable<Object>) upper;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return cb.between(from.get(field), lower, upper);
    }
}
