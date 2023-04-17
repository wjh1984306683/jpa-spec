package com.wjh.spec.specification;

import com.wjh.spec.LambdaUtils;
import com.wjh.spec.annotation.Getter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EqualSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final Object[] values;

    public EqualSpecification(String property, Object... values) {
        this.property = property;
        this.values = values;
    }

    public EqualSpecification(Getter<T, ?> getter, Object... values) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.values = values;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        if (values == null) {
            return cb.isNull(from.get(field));
        }
        if (values.length == 1) {
            return getPredicate(from, cb, values[0], field);
        }

        Predicate[] predicates = new Predicate[values.length];
        for (int i = 0; i < values.length; i++) {
            predicates[i] = getPredicate(root, cb, values[i], field);
        }
        return cb.or(predicates);
    }

    private Predicate getPredicate(From root, CriteriaBuilder cb, Object value, String field) {
        return value == null ? cb.isNull(root.get(field)) : cb.equal(root.get(field), value);
    }
}

