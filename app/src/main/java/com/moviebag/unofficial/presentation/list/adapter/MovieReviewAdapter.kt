package com.moviebag.unofficial.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.moviebag.unofficial.base.adapter.BaseViewHolder
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.presentation.list.adapter.holder.LoadMoreViewHolder
import com.moviebag.unofficial.R
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import com.moviebag.unofficial.databinding.ItemLoadMoreBinding
import com.moviebag.unofficial.databinding.ItemMovieCastListBinding
import com.moviebag.unofficial.presentation.common.MovieItemReviewViewHolder
import com.moviebag.unofficial.util.MovieReviewItemDiffCallback

class MovieReviewAdapter : PagedListAdapter<ReviewViewItem, BaseViewHolder<*>>(MovieReviewItemDiffCallback.diffCallback) {

    enum class ItemType {
        TYPE_MOVIE {
            override fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)?): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemMovieCastListBinding>(layoutInflater, R.layout.item_movie_cast_list, parent, false)
                return MovieItemReviewViewHolder(binding)
            }
        },
        TYPE_LOAD_MORE {
            override fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)?): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(layoutInflater, R.layout.item_load_more, parent, false)
                return LoadMoreViewHolder(binding)
            }
        };

        abstract fun onCreateViewHolder(parent: ViewGroup, layoutInflater: LayoutInflater, onMovieItemClick: ((MovieViewItem) -> Unit)? = null): BaseViewHolder<*>
        fun viewType(): Int = ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemType.values()[viewType].onCreateViewHolder(parent, layoutInflater)
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount > 1 && itemCount == position + 1) {
            ItemType.TYPE_LOAD_MORE.viewType()
        } else ItemType.TYPE_MOVIE.viewType()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() != 0) super.getItemCount() + 1 else 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MovieItemReviewViewHolder -> holder.bind(getItem(position))
        }
    }
}