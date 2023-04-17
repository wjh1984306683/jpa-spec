package com.wjh.spec;


import com.wjh.spec.annotation.Getter;
import com.wjh.spec.specification.BetweenSpecification;
import com.wjh.spec.specification.EqualSpecification;
import com.wjh.spec.specification.GeSpecification;
import com.wjh.spec.specification.GtSpecification;
import com.wjh.spec.specification.InSpecification;
import com.wjh.spec.specification.LeSpecification;
import com.wjh.spec.specification.LikeSpecification;
import com.wjh.spec.specification.LtSpecification;
import com.wjh.spec.specification.NotEqualSpecification;
import com.wjh.spec.specification.NotInSpecification;
import com.wjh.spec.specification.NotLikeSpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author wangjiahao
 * @since 2023/4/14 17:15
 */
public class PredicateBuilder<T> {
    private final Predicate.BooleanOperator operator;
    private final List<Specification<T>> specifications;

    public PredicateBuilder(Predicate.BooleanOperator operator) {
        this.operator = operator;
        this.specifications = new ArrayList<>();
    }

    public PredicateBuilder<T> eq(String property, Object... values) {
        return this.eq(true, property, values);
    }

    public PredicateBuilder<T> eq(Getter<T, ?> getter, Object... values) {
        return this.eq(true, getter, values);
    }

    public PredicateBuilder<T> eq(boolean condition, String property, Object... values) {
        return this.predicate(condition, new EqualSpecification<>(property, values));
    }

    public PredicateBuilder<T> eq(boolean condition, String property, Supplier<Object> supplier) {
        return this.predicate(condition, new EqualSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> eq(boolean condition, Getter<T, ?> getter, Object... values) {
        return this.predicate(condition, new EqualSpecification<>(getter, values));
    }

    public PredicateBuilder<T> eq(boolean condition, Getter<T, ?> getter, Supplier<Object> supplier) {
        return this.predicate(condition, new EqualSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> ne(String property, Object... values) {
        return this.ne(true, property, values);
    }

    public PredicateBuilder<T> ne(Getter<T, ?> getter, Object... values) {
        return this.ne(true, getter, values);
    }

    public PredicateBuilder<T> ne(boolean condition, String property, Object... values) {
        return this.predicate(condition, new NotEqualSpecification<>(property, values));
    }

    public PredicateBuilder<T> ne(boolean condition, String property, Supplier<Object> supplier) {
        return this.predicate(condition, new NotEqualSpecification<>(property, supplier));
    }

    public PredicateBuilder<T> ne(boolean condition, Getter<T, ?> getter, Object... values) {
        return this.predicate(condition, new NotEqualSpecification<>(getter, values));
    }

    public PredicateBuilder<T> ne(boolean condition, Getter<T, ?> getter, Supplier<Object> supplier) {
        return this.predicate(condition, new NotEqualSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> gt(String property, Comparable<?> compare) {
        return this.gt(true, property, compare);
    }

    public PredicateBuilder<T> gt(Getter<T, ?> getter, Comparable<?> compare) {
        return this.gt(true, getter, compare);
    }

    public PredicateBuilder<T> gt(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new GtSpecification<>(property, compare));
    }

    public PredicateBuilder<T> gt(boolean condition, String property, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new GtSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> gt(boolean condition, Getter<T, ?> getter, Comparable<?> compare) {
        return this.predicate(condition, new GtSpecification<>(getter, compare));
    }

    public PredicateBuilder<T> gt(boolean condition, Getter<T, ?> getter, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new GtSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> ge(String property, Comparable<?> compare) {
        return this.ge(true, property, compare);
    }

    public PredicateBuilder<T> ge(Getter<T, ?> getter, Comparable<?> compare) {
        return this.ge(true, getter, compare);
    }

    public PredicateBuilder<T> ge(boolean condition, String property, Comparable<? extends Object> compare) {
        return this.predicate(condition, new GeSpecification<>(property, compare));
    }

    public PredicateBuilder<T> ge(boolean condition, Getter<T, ?> getter, Comparable<? extends Object> compare) {
        return this.predicate(condition, new GeSpecification<>(getter, compare));
    }

    public PredicateBuilder<T> ge(boolean condition, String property, Supplier<Comparable<? extends Object>> supplier) {
        return this.predicate(condition, new GeSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> ge(boolean condition, Getter<T, ?> getter, Supplier<Comparable<? extends Object>> supplier) {
        return this.predicate(condition, new GeSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> lt(String property, Comparable<?> number) {
        return this.lt(true, property, number);
    }

    public PredicateBuilder<T> lt(Getter<T, ?> getter, Comparable<?> number) {
        return this.lt(true, getter, number);
    }

    public PredicateBuilder<T> lt(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new LtSpecification<>(property, compare));
    }

    public PredicateBuilder<T> lt(boolean condition, String property, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new LtSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> lt(boolean condition, Getter<T, ?> getter, Comparable<?> compare) {
        return this.predicate(condition, new LtSpecification<>(getter, compare));
    }

    public PredicateBuilder<T> lt(boolean condition, Getter<T, ?> getter, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new LtSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> le(String property, Comparable<?> compare) {
        return this.le(true, property, compare);
    }

    public PredicateBuilder<T> le(Getter<T, ?> getter, Comparable<?> compare) {
        return this.le(true, getter, compare);
    }

    public PredicateBuilder<T> le(boolean condition, String property, Comparable<?> compare) {
        return this.predicate(condition, new LeSpecification<>(property, compare));
    }

    public PredicateBuilder<T> le(boolean condition, Getter<T, ?> getter, Comparable<?> compare) {
        return this.predicate(condition, new LeSpecification<>(getter, compare));
    }

    public PredicateBuilder<T> le(boolean condition, String property, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new LeSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> le(boolean condition, Getter<T, ?> getter, Supplier<Comparable<?>> supplier) {
        return this.predicate(condition, new LeSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> between(String property, Object lower, Object upper) {
        return this.between(true, property, lower, upper);
    }

    public PredicateBuilder<T> between(Getter<T, ?> getter, Object lower, Object upper) {
        return this.between(true, getter, lower, upper);
    }

    public PredicateBuilder<T> between(boolean condition, String property, Object lower, Object upper) {
        return this.predicate(condition, new BetweenSpecification<>(property, lower, upper));
    }

    public PredicateBuilder<T> between(boolean condition, Getter<T, ?> getter, Object lower, Object upper) {
        return this.predicate(condition, new BetweenSpecification<>(getter, lower, upper));
    }

    public PredicateBuilder<T> like(String property, Object... patterns) {
        return this.like(true, property, patterns);
    }

    public PredicateBuilder<T> like(Getter<T, ?> getter, Object... patterns) {
        return this.like(true, getter, patterns);
    }

    public PredicateBuilder<T> like(boolean condition, String property, Object... values) {
        return this.predicate(condition, new LikeSpecification<>(property, values));
    }

    public PredicateBuilder<T> like(boolean condition, String property, Supplier<Object> supplier) {
        return this.predicate(condition, new LikeSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> like(boolean condition, Getter<T, ?> getter, Object... patterns) {
        return this.predicate(condition, new LikeSpecification<>(getter, patterns));
    }

    public PredicateBuilder<T> like(boolean condition, Getter<T, ?> getter, Supplier<Object> supplier) {
        return this.predicate(condition, new LikeSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> notLike(String property, Object... patterns) {
        return this.notLike(true, property, patterns);
    }

    public PredicateBuilder<T> notLike(Getter<T, ?> getter, Object... patterns) {
        return this.notLike(true, getter, patterns);
    }

    public PredicateBuilder<T> notLike(boolean condition, String property, Object... patterns) {
        return this.predicate(condition, new NotLikeSpecification<>(property, patterns));
    }

    public PredicateBuilder<T> notLike(boolean condition, String property, Supplier<Object> supplier) {
        return this.predicate(condition, new NotLikeSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> notLike(boolean condition, Getter<T, ?> getter, Object... patterns) {
        return this.predicate(condition, new NotLikeSpecification<>(getter, patterns));
    }

    public PredicateBuilder<T> notLike(boolean condition, Getter<T, ?> getter, Supplier<Object> supplier) {
        return this.predicate(condition, new NotLikeSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> in(String property, Object... values) {
        return this.in(true, property, values);
    }

    public PredicateBuilder<T> in(String property, Collection<Object> values) {
        return this.in(true, property, values);
    }

    public PredicateBuilder<T> in(Getter<T, ?> getter, Object... values) {
        return this.in(true, getter, values);
    }

    public PredicateBuilder<T> in(Getter<T, ?> getter, Collection<Object> values) {
        return this.in(true, getter, values);
    }

    public PredicateBuilder<T> in(boolean condition, String property, Object... values) {
        return this.predicate(condition, new InSpecification<>(property, values));
    }

    public PredicateBuilder<T> in(boolean condition, String property, Collection<Object> values) {
        return this.predicate(condition, new InSpecification<>(property, values));
    }

    public PredicateBuilder<T> in(boolean condition, String property, Supplier<Collection<Object>> supplier) {
        return this.predicate(condition, new InSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> in(boolean condition, Getter<T, ?> getter, Object... values) {
        return this.predicate(condition, new InSpecification<>(getter, values));
    }

    public PredicateBuilder<T> in(boolean condition, Getter<T, ?> getter, Supplier<Collection<Object>> supplier) {
        return this.predicate(condition, new InSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> notIn(String property, Object... values) {
        return this.notIn(true, property, values);
    }

    public PredicateBuilder<T> notIn(String property, Collection<Object> values) {
        return this.notIn(true, property, values);
    }

    public PredicateBuilder<T> notIn(Getter<T, ?> getter, Object... values) {
        return this.notIn(true, getter, values);
    }

    public PredicateBuilder<T> notIn(Getter<T, ?> getter, Collection<Object> values) {
        return this.notIn(true, getter, values);
    }

    public PredicateBuilder<T> notIn(boolean condition, String property, Object... values) {
        return this.predicate(condition, new NotInSpecification<>(property, values));
    }

    public PredicateBuilder<T> notIn(boolean condition, String property, Collection<Object> values) {
        return this.predicate(condition, new NotInSpecification<>(property, values));
    }

    public PredicateBuilder<T> notIn(boolean condition, String property, Supplier<Collection<Object>> supplier) {
        return this.predicate(condition, new NotInSpecification<>(property, supplier.get()));
    }

    public PredicateBuilder<T> notIn(boolean condition, Getter<T, ?> getter, Object... values) {
        return this.predicate(condition, new NotInSpecification<>(getter, values));
    }

    public PredicateBuilder<T> notIn(boolean condition, Getter<T, ?> getter, Collection<Object> values) {
        return this.predicate(condition, new NotInSpecification<>(getter, values));
    }

    public PredicateBuilder<T> notIn(boolean condition, Getter<T, ?> getter, Supplier<Collection<Object>> supplier) {
        return this.predicate(condition, new NotInSpecification<>(getter, supplier.get()));
    }

    public PredicateBuilder<T> predicate(Specification<T> specification) {
        return this.predicate(true, specification);
    }

    public PredicateBuilder<T> predicate(boolean condition, Specification<T> specification) {
        if (condition) {
            this.specifications.add(specification);
        }

        return this;
    }

    public Specification<T> build() {
        return (root, query, cb) -> {
            Predicate[] predicates = new Predicate[this.specifications.size()];

            for (int i = 0; i < this.specifications.size(); ++i) {
                predicates[i] = this.specifications.get(i).toPredicate(root, query, cb);
            }

            if (Objects.equals(predicates.length, 0)) {
                return null;
            } else {
                return Predicate.BooleanOperator.OR.equals(this.operator) ? cb.or(predicates) : cb.and(predicates);
            }
        };
    }
}
