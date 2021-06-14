package com.moviebag.unofficial.data.model

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("content") val comment: String?,
    @SerializedName("author_details") val author: AuthorDetails?,
    @SerializedName("created_at") val createdAt: String?)

data class AuthorDetails(@SerializedName("name") val name: String?,
                         @SerializedName("username") val username: String?,
                         @SerializedName("avatar_path") val profilePath: String?)