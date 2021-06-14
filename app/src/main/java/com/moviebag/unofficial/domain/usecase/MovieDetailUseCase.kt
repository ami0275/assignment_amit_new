package com.moviebag.unofficial.domain.usecase

import com.moviebag.unofficial.data.model.uimodel.MovieDetailViewItem
import com.moviebag.unofficial.data.repository.RemoteRepository
import com.moviebag.unofficial.domain.mapper.MovieDetailItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val itemMapper: MovieDetailItemMapper
) {
    fun getMovieDetail(movieId: Int): Observable<MovieDetailViewItem> {
        return repository.getMovieDetail(movieId = movieId).map { itemMapper.mapFrom(it) }
    }
}