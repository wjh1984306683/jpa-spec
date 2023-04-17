package com.wjh.spec.annotation;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @since 2023/4/14 17:57
 */
@FunctionalInterface
public interface Getter<T, R> extends Serializable {
    R apply(T t);
}
