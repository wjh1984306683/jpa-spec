package com.wjh.spec.specification;

import com.wjh.spec.LambdaUtils;
import com.wjh.spec.annotation.Getter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class NotInSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final Collection<Object> values;

    public NotInSpecification(String property, Object[] values) {
        this.property = property;
        this.values = Arrays.asList(values);
    }

    public NotInSpecification(String property, Collection<Object> values) {
        this.property = property;
        this.values = Collections.singletonList(values);
    }

    public NotInSpecification(Getter<T, ?> getter, Object[] values) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.values = Arrays.asList(values);
    }

    public NotInSpecification(Getter<T, ?> getter, Collection<Object> values) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.values = Collections.singletonList(values);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        return from.get(field).in(values).not();
    }
}
