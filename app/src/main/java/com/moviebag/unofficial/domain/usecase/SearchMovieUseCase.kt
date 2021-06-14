package com.moviebag.unofficial.domain.usecase

import com.moviebag.unofficial.data.model.uimodel.MovieListViewItem
import com.moviebag.unofficial.data.repository.RemoteRepository
import com.moviebag.unofficial.domain.mapper.MovieItemMapper
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchMovieUseCase @Inject constructor(
    private val repository: RemoteRepository,
    private val itemMapper: MovieItemMapper
) {
    fun searchMovie(query: String, page: Int): Observable<MovieListViewItem> {
        return repository.searchMovie(query, page).map {
            itemMapper.mapFrom(it)
        }
    }
}

