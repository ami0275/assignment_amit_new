package com.moviebag.unofficial.domain.mapper

import com.moviebag.unofficial.base.extension.orZero
import com.moviebag.unofficial.base.extension.simpleDateConvert
import com.moviebag.unofficial.data.model.response.MovieDetailReponse
import com.moviebag.unofficial.data.model.uimodel.MovieDetailViewItem
import com.moviebag.unofficial.domain.decider.MovieDetailItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailItemMapper @Inject constructor(private val itemDecider: MovieDetailItemDecider) : Mapper<MovieDetailReponse, MovieDetailViewItem> {

    override fun mapFrom(item: MovieDetailReponse): MovieDetailViewItem {
        return MovieDetailViewItem(
            id = item.id.orZero(),
            backdropPath = itemDecider.provideBackdropPath(item.backdropPath).orEmpty(),
            genres = item.genres.orEmpty(),
            title = item.title.orEmpty(),
            overview = item.overview.orEmpty(),
            popularity = item.popularity.orZero(),
            imagePath = itemDecider.provideImagePath(item.posterPath).orEmpty(),
            releaseDate = item.releaseDate.simpleDateConvert("dd MMM yyyy").orEmpty(),
            runtime = "${item.runtime} min",
            status = item.status.orEmpty(),
            voteAverage = item.voteAverage?.toFloat().orZero() / 2
        )
    }
}