package com.moviebag.unofficial.data.model.response

import com.moviebag.unofficial.data.model.Cast
import com.google.gson.annotations.SerializedName
import com.moviebag.unofficial.data.model.Review

data class ReviewResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("results") val review: List<Review>?,
    @SerializedName("id") val id: Int?
)