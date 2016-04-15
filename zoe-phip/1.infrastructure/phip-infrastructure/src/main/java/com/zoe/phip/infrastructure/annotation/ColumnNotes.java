package com.zoe.phip.infrastructure.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by huangyinfu on 2016/4/15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface  ColumnNotes {

    String name ()default "";
}
