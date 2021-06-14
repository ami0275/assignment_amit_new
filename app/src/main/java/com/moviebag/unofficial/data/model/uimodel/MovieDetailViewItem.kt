package com.moviebag.unofficial.data.model.uimodel

import com.moviebag.unofficial.data.model.Genre

data class MovieDetailViewItem(
    val id: Int,
    val backdropPath: String,
    val genres: List<Genre>,
    val title: String,
    val overview: String,
    val popularity: Double,
    val imagePath: String,
    val releaseDate: String,
    val runtime: String,
    val status: String,
    val voteAverage: Float
) {
    fun getTextBottomOfTitle() = "$releaseDate • $runtime"
}