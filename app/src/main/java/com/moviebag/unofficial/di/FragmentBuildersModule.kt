package com.moviebag.unofficial.di

import com.moviebag.unofficial.presentation.detail.MovieDetailFragment
import com.moviebag.unofficial.presentation.list.MovieListFragment
import com.moviebag.unofficial.presentation.movies.MoviesFragment
import com.moviebag.unofficial.presentation.review.MovieReviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): MoviesFragment

   /* @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment*/

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieReviewFragment(): MovieReviewFragment
}
