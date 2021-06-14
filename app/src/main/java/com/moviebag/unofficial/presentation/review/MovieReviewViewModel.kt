package com.moviebag.unofficial.presentation.review

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.moviebag.unofficial.base.viewmodel.BaseViewModel
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import com.moviebag.unofficial.domain.usecase.MovieReviewsUseCase
import javax.inject.Inject

class MovieReviewViewModel @Inject constructor(
    private val movieReviewUseCase: MovieReviewsUseCase) : BaseViewModel() {
    /** LiveData for ViewState **/
    private val liveDataViewState = MutableLiveData<MovieReviewFragmentViewState>()
    val liveDataViewState_: LiveData<MovieReviewFragmentViewState> = liveDataViewState
    private var dataSource: MovieItemReviewDataSource? = null

    var movieId = 0

    init {
        updateUIState(showLoading = true)
    }

    override fun handleIntent(extras: Bundle) {
        val args = MovieReviewFragmentArgs.fromBundle(extras)
        this.movieId = args.movieId
        this.liveDataViewState.value = MovieReviewFragmentViewState(review = emptyList())
    }

    fun getMovieReviewObserver(page: Int) = movieReviewUseCase.getMovieReviews(movieId = movieId, page = page)

    fun getMovieReviews(): LiveData<PagedList<ReviewViewItem>> {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(true)
            .build()
        return initPagedListBuilder(config).build()
    }

    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, ReviewViewItem> {
        val dataSourceFactory = object : DataSource.Factory<Int, ReviewViewItem>() {
            override fun create(): DataSource<Int, ReviewViewItem> {
                dataSource = MovieItemReviewDataSource(this@MovieReviewViewModel)
                return dataSource!!
            }
        }
        return LivePagedListBuilder<Int, ReviewViewItem>(dataSourceFactory, config)
    }
}