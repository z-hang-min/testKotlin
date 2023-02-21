package com.mm.okhttp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by zm on 2023/2/14
 *
 * @Description:
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnn {
    String value();
}
