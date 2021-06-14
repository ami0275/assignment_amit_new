package com.moviebag.unofficial.domain.usecase

import com.moviebag.unofficial.data.model.uimodel.CastViewItem
import com.moviebag.unofficial.data.repository.RemoteRepository
import com.moviebag.unofficial.domain.mapper.CastItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCreditUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val castItemMapper: CastItemMapper
) {
    fun getMovieCredits(movieId: Int): Observable<List<CastViewItem>> {
        return repository.getMovieCredits(movieId).map {
            castItemMapper.mapFrom(it).orEmpty()
        }
    }
}

