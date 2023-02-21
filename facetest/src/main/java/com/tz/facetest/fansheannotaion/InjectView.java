package com.tz.facetest.fansheannotaion;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.StringRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by zm on 2023/2/14
 *
 * @Description:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface InjectView {
    @IdRes int value();
    @StringRes int txt();
}
