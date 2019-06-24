package com.xandone.dog.wcapp.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * author: xandone
 * created on: 2019/3/6 9:42
 */
@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
