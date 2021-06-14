package com.moviebag.unofficial.domain.decider

import com.moviebag.unofficial.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieItemDecider @Inject constructor() {
    fun provideImagePath(path: String?): String? = "${Constants.POSTER_PATH}$path"
}