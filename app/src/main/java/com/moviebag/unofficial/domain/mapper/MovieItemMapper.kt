package com.moviebag.unofficial.domain.mapper

import com.moviebag.unofficial.base.extension.orFalse
import com.moviebag.unofficial.base.extension.orTrue
import com.moviebag.unofficial.base.extension.orZero
import com.moviebag.unofficial.base.extension.simpleDateConvert
import com.moviebag.unofficial.data.model.response.MovieResponse
import com.moviebag.unofficial.data.model.uimodel.MovieListViewItem
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.data.model.uimodel.displayVoteCount
import com.moviebag.unofficial.domain.decider.MovieItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieItemMapper @Inject constructor(private val itemDecider: MovieItemDecider) :
    Mapper<MovieResponse, MovieListViewItem?> {
    override fun mapFrom(item: MovieResponse): MovieListViewItem? {
        return MovieListViewItem(
            page = item.page.orZero(),
            totalPage = item.totalPages.orZero(),
            movies = item.results?.map { movie ->
                MovieViewItem(
                    id = movie.id.orZero(),
                    imagePath = itemDecider.provideImagePath(movie.posterPath).orEmpty(),
                    title = movie.title.orEmpty().ifEmpty { "NA" },
                    releaseDate = "Releasing on: " + movie.releaseDate.simpleDateConvert("dd MMM yyyy").orEmpty().ifEmpty { "NA" },
                    votes = "Votes: " + movie.displayVoteCount() + " reviews",
                    language = "Language: English" /*movie.originalLanguage.orEmpty().ifEmpty { "NA" }*/,
                    popularity = "Adult: " + if (movie.adult.orTrue()) "Below 18" else "Above 18"
                )
            }.orEmpty()
        )
    }
}