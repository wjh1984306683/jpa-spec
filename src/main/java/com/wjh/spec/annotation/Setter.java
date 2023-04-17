package com.wjh.spec.annotation;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @since 2023/4/16 22:07
 */
@FunctionalInterface
public interface Setter<T, R> extends Serializable {

    void accept(T t, R r);

}
