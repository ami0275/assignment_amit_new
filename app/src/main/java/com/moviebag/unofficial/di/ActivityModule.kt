package com.moviebag.unofficial.di

import com.moviebag.unofficial.presentation.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun bindHomeActivity(): HomeActivity
}