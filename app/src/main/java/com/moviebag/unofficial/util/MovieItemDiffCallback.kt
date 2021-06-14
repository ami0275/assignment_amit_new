package com.moviebag.unofficial.util

import androidx.recyclerview.widget.DiffUtil
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem

object MovieItemDiffCallback {
    val diffCallback = object : DiffUtil.ItemCallback<MovieViewItem>() {
        override fun areItemsTheSame(oldItem: MovieViewItem, newItem: MovieViewItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: MovieViewItem, newItem: MovieViewItem): Boolean = oldItem == newItem
    }
}

object MovieReviewItemDiffCallback {
    val diffCallback = object : DiffUtil.ItemCallback<ReviewViewItem>() {
        override fun areItemsTheSame(oldItem: ReviewViewItem, newItem: ReviewViewItem): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: ReviewViewItem, newItem: ReviewViewItem): Boolean = oldItem == newItem
    }
}