package com.moviebag.unofficial.presentation.common

import com.moviebag.unofficial.base.adapter.BaseViewHolder
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import com.moviebag.unofficial.databinding.ItemMovieCastListBinding
import com.moviebag.unofficial.databinding.ItemMovieListBinding

class MovieItemReviewViewHolder(val binding: ItemMovieCastListBinding) : BaseViewHolder<ReviewViewItem?>(binding.root) {

    override fun bind(data: ReviewViewItem?) {
        if (data == null) return
        binding.movie = data
        binding.executePendingBindings()
    }
}