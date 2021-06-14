package com.moviebag.unofficial.data.model.uimodel

data class MovieListViewItem(
    val page: Int,
    val totalPage: Int,
    val movies: List<MovieViewItem>
)

data class MovieReviewViewItem(
    val page: Int,
    val totalPage: Int,
    val movies: List<ReviewViewItem>
)