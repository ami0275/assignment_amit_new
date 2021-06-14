package com.moviebag.unofficial.domain.mapper

import com.moviebag.unofficial.base.extension.orZero
import com.moviebag.unofficial.data.model.response.ReviewResponse
import com.moviebag.unofficial.data.model.uimodel.*
import com.moviebag.unofficial.domain.decider.ReviewItemDecider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewItemMapper @Inject constructor(private val itemDecider: ReviewItemDecider) :
    Mapper<ReviewResponse, MovieReviewViewItem?> {
    override fun mapFrom(item: ReviewResponse): MovieReviewViewItem? {
        return MovieReviewViewItem(
            page = item.page.orZero(),
            totalPage = item.totalPages.orZero(),
            movies = item.review?.map { cast ->
                ReviewViewItem(
                    name = cast.author?.name.orEmpty().ifEmpty { "NA" },
                    comment = cast.comment.orEmpty().ifEmpty { "NA" },
                    profilePath = itemDecider.provideImagePath(cast.author?.profilePath).orEmpty().ifEmpty { "NA" },
                    createdAt = cast.createdAt.toString().ifEmpty { "NA" }
                )
            } ?: emptyList()
        )
    }
}