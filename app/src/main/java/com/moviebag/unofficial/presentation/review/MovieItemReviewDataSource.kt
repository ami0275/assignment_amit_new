package com.moviebag.unofficial.presentation.review

import androidx.paging.PageKeyedDataSource
import com.moviebag.unofficial.base.extension.applySchedulers
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem

class MovieItemReviewDataSource(private val viewModel: MovieReviewViewModel) : PageKeyedDataSource<Int, ReviewViewItem>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ReviewViewItem>) {
        viewModel.getCompositeDisposable().add(
            viewModel.getMovieReviewObserver(page = 1)
                .applySchedulers().subscribe(
                    { data ->
                        val nextPage = if (data.totalPage > data.page.plus(1)) data.page.plus(1) else null
                        callback.onResult(data.movies, null, nextPage)
                        viewModel.updateUIState(showContent = true)
                    }, { throwable ->
                        viewModel.updateUIState(showError = true, throwable = throwable)
                    })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewViewItem>) {
        viewModel.getCompositeDisposable().add(
            viewModel.getMovieReviewObserver(page = params.key)
                .applySchedulers().subscribe(
                    { data ->
                        val nextPage = if (data.totalPage > data.page.plus(1)) data.page.plus(1) else null
                        callback.onResult(data.movies, nextPage)
                    },
                    { throwable ->
                        viewModel.updateUIState(showError = true, throwable = throwable)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ReviewViewItem>) {}
}