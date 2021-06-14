package com.moviebag.unofficial.presentation.movies

import com.moviebag.unofficial.data.model.uimodel.MovieListViewItem

class MoviesItemCombiner : (MovieListViewItem, MovieListViewItem, MovieListViewItem) -> MoviesFragmentViewState {
    override fun invoke(p1: MovieListViewItem, p2: MovieListViewItem, p3: MovieListViewItem): MoviesFragmentViewState {
        return MoviesFragmentViewState(popularMovies = p1, nowPlayingMovies = p2, upComingMovies = p3)
    }
}