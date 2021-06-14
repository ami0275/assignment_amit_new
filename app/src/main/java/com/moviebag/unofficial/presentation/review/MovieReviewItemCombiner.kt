package com.moviebag.unofficial.presentation.review

import com.moviebag.unofficial.data.model.uimodel.CastViewItem
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem

class MovieReviewItemCombiner : (CastViewItem, List<ReviewViewItem>) -> MovieReviewFragmentViewState {
    override fun invoke(p0 : CastViewItem, p1: List<ReviewViewItem>): MovieReviewFragmentViewState {
        return MovieReviewFragmentViewState(review = p1)
    }
}