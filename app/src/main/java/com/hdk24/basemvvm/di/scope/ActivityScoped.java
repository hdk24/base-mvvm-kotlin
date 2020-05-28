package com.hdk24.basemvvm.di.scope;

/*
 *  Created by Hanantadk on 14/04/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 14/04/20.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
