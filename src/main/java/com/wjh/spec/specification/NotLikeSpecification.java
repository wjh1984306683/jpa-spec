package com.wjh.spec.specification;

import com.wjh.spec.LambdaUtils;
import com.wjh.spec.annotation.Getter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class NotLikeSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final Object[] patterns;

    public NotLikeSpecification(String property, Object... patterns) {
        this.property = property;
        this.patterns = patterns;
    }

    public NotLikeSpecification(Getter<T, ?> getter, Object... patterns) {
        this.property = LambdaUtils.convertToFieldName(getter);
        this.patterns = patterns;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        From from = getRoot(property, root);
        String field = getProperty(property);
        if (patterns.length == 1) {
            return cb.like(from.get(field), patterns[0].toString()).not();
        }
        Predicate[] predicates = new Predicate[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            predicates[i] = cb.like(from.get(field), patterns[i].toString()).not();
        }
        return cb.or(predicates);
    }
}
