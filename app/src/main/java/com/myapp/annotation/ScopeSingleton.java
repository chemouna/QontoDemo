package com.myapp.annotation;

import javax.inject.Scope;

@Scope
public @interface ScopeSingleton {
    Class<?> value();
}
