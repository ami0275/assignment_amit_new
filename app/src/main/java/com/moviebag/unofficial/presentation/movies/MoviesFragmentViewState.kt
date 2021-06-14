package com.moviebag.unofficial.presentation.movies

import com.moviebag.unofficial.data.model.uimodel.MovieListViewItem

class MoviesFragmentViewState(
    private val popularMovies: MovieListViewItem,
    private val nowPlayingMovies: MovieListViewItem,
    private val upComingMovies: MovieListViewItem
) {
    fun getPopularMovies(): MovieListViewItem = popularMovies

    fun getNowPlayingMovies(): MovieListViewItem = nowPlayingMovies

    fun getUpComingMovies(): MovieListViewItem = upComingMovies

    fun getPopularMoviesViewPagerTitle() = "Popular Movies"

    fun getUpComingMoviesViewPagerTitle() = "Upcoming Movies"
}

