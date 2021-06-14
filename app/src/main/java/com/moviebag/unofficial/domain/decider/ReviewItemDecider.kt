package com.moviebag.unofficial.domain.decider

import com.moviebag.unofficial.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewItemDecider @Inject constructor() {
    fun provideImagePath(path: String?): String? = "${Constants.PROFILE_PATH}$path"
}