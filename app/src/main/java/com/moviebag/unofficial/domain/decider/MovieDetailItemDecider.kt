package com.moviebag.unofficial.domain.decider

import com.moviebag.unofficial.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailItemDecider @Inject constructor() {
    fun provideImagePath(path: String?): String? = "${Constants.POSTER_PATH}$path"
    fun provideBackdropPath(path: String?): String? = "${Constants.BACKDROP_PATH}$path"
}