package com.moviebag.unofficial.presentation.list.adapter

import androidx.paging.PageKeyedDataSource
import com.moviebag.unofficial.base.extension.applySchedulers
import com.moviebag.unofficial.data.enum.MovieListPageType
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.presentation.list.MovieListViewModel

class MovieItemDataSource(private val viewModel: MovieListViewModel, private val pageType: MovieListPageType?, private val searchQuery: String?) : PageKeyedDataSource<Int, MovieViewItem>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieViewItem>) {
        pageType?.let { movieListPageType ->
            viewModel.getCompositeDisposable().add(
                viewModel.getMovies(page = 1, pageType = movieListPageType, searchQuery = searchQuery)
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
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieViewItem>) {
        pageType?.let { movieListPageType ->
            viewModel.getCompositeDisposable().add(
                viewModel.getMovies(page = params.key, pageType = movieListPageType, searchQuery = searchQuery)
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
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieViewItem>) {}
}