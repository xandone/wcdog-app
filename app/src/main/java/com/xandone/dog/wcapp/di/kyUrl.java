package com.xandone.dog.wcapp.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * author: xandone
 * created on: 2019/3/19 22:12
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface kyUrl {
}
