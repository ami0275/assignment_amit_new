package com.moviebag.unofficial.presentation.review

import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import java.io.Serializable

class MovieReviewFragmentViewState(
    private val review: List<ReviewViewItem>) : Serializable {

    fun reviews() = review
    fun getReviewTitle() = "Reviews"
}