package com.moviebag.unofficial.domain.usecase

import com.moviebag.unofficial.data.model.uimodel.MovieReviewViewItem
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import com.moviebag.unofficial.data.repository.RemoteRepository
import com.moviebag.unofficial.domain.mapper.ReviewItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieReviewsUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val reviewItemMapper: ReviewItemMapper
) {
    fun getMovieReviews(movieId: Int, page: Int): Observable<MovieReviewViewItem> {
        return repository.getMoviesReview(movieId, page).map {
            reviewItemMapper.mapFrom(it)
        }
    }
}

