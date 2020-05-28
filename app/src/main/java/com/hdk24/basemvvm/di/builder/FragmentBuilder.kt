package com.hdk24.basemvvm.di.builder

import com.hdk24.basemvvm.ui.MovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMovieFragment() : MovieFragment
}