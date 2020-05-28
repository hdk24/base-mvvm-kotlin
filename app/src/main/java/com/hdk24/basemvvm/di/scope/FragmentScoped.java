package com.hdk24.basemvvm.di.scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Scope;

/*
 *  Created by Hanantadk on 14/04/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 14/04/20.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface FragmentScoped {
}

