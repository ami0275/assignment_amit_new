package com.moviebag.unofficial.presentation.common

import com.moviebag.unofficial.base.adapter.BaseViewHolder
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.databinding.ItemListViewMovieBinding

class MovieItemListViewHolder(val binding: ItemListViewMovieBinding, val itemClick: ((MovieViewItem) -> Unit)?) : BaseViewHolder<MovieViewItem?>(binding.root) {

    override fun bind(data: MovieViewItem?) {
        if (data == null) return

        binding.movie = data
        binding.root.setOnClickListener {
            itemClick?.invoke(data)
        }

        binding.executePendingBindings()
    }
}