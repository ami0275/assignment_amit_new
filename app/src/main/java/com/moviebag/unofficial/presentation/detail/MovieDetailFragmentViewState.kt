package com.moviebag.unofficial.presentation.detail

import android.view.View
import com.moviebag.unofficial.data.model.uimodel.CastViewItem
import com.moviebag.unofficial.data.model.uimodel.MovieDetailViewItem
import com.moviebag.unofficial.data.model.uimodel.MovieListViewItem
import java.io.Serializable

class MovieDetailFragmentViewState(
    private val movieDetailViewItem: MovieDetailViewItem,
    private val similarMovies: MovieListViewItem,
    private val casts: List<CastViewItem>,
    private val recommendationMovies: MovieListViewItem
) : Serializable {
    fun getMovieDetailItems(): MovieDetailViewItem = movieDetailViewItem
    fun getSimilarMovies(): MovieListViewItem = similarMovies
    fun getCasts(): List<CastViewItem> = casts
    fun getRecommendationMovies(): MovieListViewItem = recommendationMovies

    fun getSimilarMoviesViewPagerTitle() = "Similar Movies"

    fun getRecommendationMoviesViewPagerTitle() = "Recommendation Movies"

    fun getCastTitle() = "Casts"
    fun getReviewTitle() = "Reviews"

    fun similarMoviesViewPagerVisibility(): Int = if (similarMovies.movies.isNotEmpty()) View.VISIBLE else View.GONE

    fun recommendationMoviesViewPagerVisibility(): Int = if (recommendationMovies.movies.isNotEmpty()) View.VISIBLE else View.GONE

    fun castAreaVisibility(): Int = if (casts.isNotEmpty()) View.VISIBLE else View.GONE
}