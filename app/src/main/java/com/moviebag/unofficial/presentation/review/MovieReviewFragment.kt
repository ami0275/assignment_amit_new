package com.moviebag.unofficial.presentation.review

import androidx.lifecycle.observe
import androidx.paging.PagedList
import com.moviebag.unofficial.R
import com.moviebag.unofficial.base.view.BaseFragment
import com.moviebag.unofficial.data.model.uimodel.ReviewViewItem
import com.moviebag.unofficial.databinding.FragmentCastBinding
import com.moviebag.unofficial.presentation.list.adapter.MovieReviewAdapter

class MovieReviewFragment : BaseFragment<FragmentCastBinding, MovieReviewViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_cast
    override val classTypeOfViewModel: Class<MovieReviewViewModel> = MovieReviewViewModel::class.java

    private val reviewAdapter by lazy { MovieReviewAdapter() }

    override fun init() {
        baseView = binding.baseView
        binding.recyclerView.adapter = reviewAdapter

        binding.imageButtonBack.setOnClickListener { activity?.onBackPressed() }
    }

    override fun setUpViewModelStateObservers() {
        viewModel.getMovieReviews().observe(viewLifecycleOwner, ::onMovieListLoaded)
        viewModel.liveDataViewState_.observe(viewLifecycleOwner) {
            setViewState(it)
        }
    }

    private fun onMovieListLoaded(movieList: PagedList<ReviewViewItem>) {
        reviewAdapter.submitList(movieList)
    }

    private fun setViewState(fragmentViewState: MovieReviewFragmentViewState) {
        binding.viewState = fragmentViewState
        binding.executePendingBindings()
    }
}