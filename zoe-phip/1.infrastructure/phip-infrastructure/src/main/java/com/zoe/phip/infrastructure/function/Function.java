package com.zoe.phip.infrastructure.function;

/**
 * Created by qiuyungen on 2016/2/29.
 */
@FunctionalInterface
public interface Function<R> {
    R apply() throws Exception;
}
