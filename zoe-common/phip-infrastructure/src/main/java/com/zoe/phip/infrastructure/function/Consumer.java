package com.zoe.phip.infrastructure.function;

/**
 * Created by Eason on 2016/3/28.
 */
@FunctionalInterface
public interface Consumer<T> {
    void accept(T arg) throws Exception;
}
