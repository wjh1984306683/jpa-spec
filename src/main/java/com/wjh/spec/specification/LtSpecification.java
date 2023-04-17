package com.wjh.spec.specification;

import com.wjh.spec.LambdaUtils;
import com.wjh.spec.annotation.Getter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LtSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final Comparable<Object> compare;

    public LtSpecification(String property, Comparable<? extends Object> compare) {
        this.property = property;
        this.compare = (Comparable<Object>) compare;
    }

    public LtSpecification(Getter<T, ?> getter, Comparable<? extends Object> compare) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.compare = (Comparable<Object>) compare;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return cb.lessThan(from.get(field), compare);
    }
}
