package com.tz.facetest.aboutt.asm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by zm on 2023/2/4
 *
 * @Description:
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface ASMTEST {
}
