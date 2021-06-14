package com.moviebag.unofficial.data.model.response

import com.moviebag.unofficial.data.model.Cast
import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast") val cast: List<Cast>?,
    @SerializedName("id") val id: Int?
)