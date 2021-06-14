package com.moviebag.unofficial.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moviebag.unofficial.base.viewmodel.BaseViewModel
import com.moviebag.unofficial.data.enum.MovieType
import com.moviebag.unofficial.domain.usecase.MovieListUseCase
import io.reactivex.rxkotlin.Observables
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val movieListUseCase: MovieListUseCase
) : BaseViewModel() {
    /** LiveData for ViewState **/
    private val liveDataViewState = MutableLiveData<MoviesFragmentViewState>()
    val liveDataViewState_: LiveData<MoviesFragmentViewState> = liveDataViewState

    fun getMovies() {
        return Observables.zip(
            movieListUseCase.getMovies(MovieType.POPULAR),
            movieListUseCase.getMovies(MovieType.NOW_PLAYING),
            movieListUseCase.getMovies(MovieType.UPCOMING),
            MoviesItemCombiner()
        ).sendRequest {
            liveDataViewState.value = it
        }
    }
}