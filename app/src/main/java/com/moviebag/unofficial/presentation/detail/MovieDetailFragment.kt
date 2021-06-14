package com.moviebag.unofficial.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.moviebag.unofficial.base.view.BaseDialogFragment
import com.moviebag.unofficial.data.model.Genre
import com.moviebag.unofficial.data.model.uimodel.CastViewItem
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.presentation.movies.adapter.MoviePagerAdapter
import com.google.android.material.chip.Chip
import com.moviebag.unofficial.R
import com.moviebag.unofficial.databinding.FragmentMovieDetailBinding
import com.moviebag.unofficial.databinding.ItemCastBinding
import com.moviebag.unofficial.presentation.list.MovieListFragmentDirections

class MovieDetailFragment : BaseDialogFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_movie_detail
    override val classTypeOfViewModel: Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    private val similarMoviesAdapter by lazy { MoviePagerAdapter(requireContext(), MoviePagerAdapter.ITEM_TYPE.SMALL) }
    private val recommendationMoviesAdapter by lazy { MoviePagerAdapter(requireContext(), MoviePagerAdapter.ITEM_TYPE.SMALL) }

    override fun init() {
        baseView = binding.baseView
    }

    override fun initStartRequest() {
        viewModel.getMovieDetail()
    }

    override fun setUpViewModelStateObservers() {
        viewModel.liveDataViewState_.observe(viewLifecycleOwner) {
            setViewState(it)
        }
    }

    override fun setupClickListeners() {
        similarMoviesAdapter.onMovieItemClick = ::onMovieItemClick
        recommendationMoviesAdapter.onMovieItemClick = ::onMovieItemClick

        binding.review.setOnClickListener {
            findNavController().navigate(
                R.id.action_movieDetailFragment_to_movieReviewFragment, Bundle().apply { putInt("movie_id", viewModel.movieId) }
            )
        }

        binding.cast.setOnClickListener {
            findNavController().navigate(
                R.id.action_movieDetailFragment_to_movieReviewFragment, Bundle().apply { putInt("movie_id", viewModel.movieId) }
            )
        }
    }

    private fun onMovieItemClick(movieItem: MovieViewItem) {
        viewModel.movieId = movieItem.id
        initStartRequest()
    }

    private fun setViewState(fragmentViewState: MovieDetailFragmentViewState) {
        binding.viewState = fragmentViewState
        binding.executePendingBindings()

        setupSimilarMoviesViewPager(fragmentViewState.getSimilarMovies().movies)
        setupRecommendationMoviesViewPager(fragmentViewState.getRecommendationMovies().movies)
        setupGenresChipGroup(fragmentViewState.getMovieDetailItems().genres)
        setupCastLayout(fragmentViewState.getCasts())
    }

    private fun setupGenresChipGroup(genres: List<Genre>) {
        binding.chipGroupGenres.apply {
            for (genre in genres) {
                val chip = Chip(context)
                chip.text = genre.name
                addView(chip)
            }
        }
    }

    private fun setupSimilarMoviesViewPager(movies: List<MovieViewItem>) {
        this.similarMoviesAdapter.setItem(movies)
        binding.layoutSimilarMovies.viewPager.adapter = similarMoviesAdapter
    }

    private fun setupRecommendationMoviesViewPager(movies: List<MovieViewItem>) {
        this.recommendationMoviesAdapter.setItem(movies)
        binding.layoutRecommendationMovies.viewPager.adapter = recommendationMoviesAdapter
    }

    private fun setupCastLayout(casts: List<CastViewItem>) {
        binding.linearLayoutCastContent.removeAllViews()
        for (cast in casts) {
            val castItem = DataBindingUtil.inflate<ItemCastBinding>(LayoutInflater.from(requireContext()), R.layout.item_cast, null, false)
            castItem.cast = cast
            binding.linearLayoutCastContent.addView(castItem.root)
        }
    }
}