package com.wjh.spec.specification;

import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.io.Serializable;

abstract class AbstractSpecification<T> implements Specification<T>, Serializable {
    public String getProperty(String property) {
        if (property.contains(".")) {
            return StringUtils.split(property, ".")[1];
        }
        return property;
    }

    public From getRoot(String property, Root<T> root) {
        if (property.contains(".")) {
            String joinProperty = StringUtils.split(property, ".")[0];
            return root.join(joinProperty, JoinType.LEFT);
        }
        return root;
    }
}
