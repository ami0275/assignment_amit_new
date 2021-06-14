package com.moviebag.unofficial.presentation.list

import com.moviebag.unofficial.data.enum.MovieListPageType
import com.moviebag.unofficial.data.enum.MovieListPageType.*

class MovieListFragmentViewState(private val pageType: MovieListPageType, private val searchQuery: String?) {
    fun getPageType(): MovieListPageType = pageType
    fun getSearchQuery(): String? = searchQuery

    fun getPageTitle(): String =
        when (pageType) {
            UPCOMING -> "Upcoming Movies"
            POPULAR -> "Popular Movies"
            SEARCH -> "Search Results For '${searchQuery?.toUpperCase()}'"
        }
}