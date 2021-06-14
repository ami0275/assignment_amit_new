package com.moviebag.unofficial.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviebag.unofficial.presentation.detail.MovieDetailViewModel
import com.moviebag.unofficial.presentation.home.HomeViewModel
import com.moviebag.unofficial.presentation.list.MovieListViewModel
import com.moviebag.unofficial.presentation.movies.MoviesViewModel
import com.moviebag.unofficial.presentation.review.MovieReviewViewModel
import com.moviebag.unofficial.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindsMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindsMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindsMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieReviewViewModel::class)
    abstract fun bindsMovieReviewViewModel(viewModel: MovieReviewViewModel): ViewModel
}