package com.moviebag.unofficial.presentation.list

import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviebag.unofficial.base.view.BaseFragment
import com.moviebag.unofficial.data.model.uimodel.MovieViewItem
import com.moviebag.unofficial.R
import com.moviebag.unofficial.databinding.FragmentMovieListBinding
import com.moviebag.unofficial.presentation.list.adapter.MovieListAdapter

class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_movie_list
    override val classTypeOfViewModel: Class<MovieListViewModel> = MovieListViewModel::class.java

    private val adapter: MovieListAdapter by lazy { MovieListAdapter() }
    private var isGridModeOn = false
        set(value) {
            field = value
            this.toggleGridMode()
        }

    override fun init() {
        baseView = binding.baseView
        binding.recyclerView.adapter = adapter
        toggleGridMode()
    }

    private fun toggleGridMode() {
        adapter.isGridModeOn = isGridModeOn
        if (isGridModeOn) {
            binding.imageButtonLayoutChanger.setImageResource(R.drawable.ic_iconfinder_ic_grid_off_48px_352412)
            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
            (binding.recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = adapter.spanSizeLookup
        } else {
            binding.imageButtonLayoutChanger.setImageResource(R.drawable.ic_iconfinder_ic_grid_on_48px_352413)
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter.notifyDataSetChanged()
    }

    override fun setupClickListeners() {
        binding.imageButtonLayoutChanger.setOnClickListener { isGridModeOn = !isGridModeOn }
        binding.imageButtonBack.setOnClickListener { activity?.onBackPressed() }
        adapter.onMovieItemClick = ::onMovieItemClick
    }

    override fun setUpViewModelStateObservers() {
        viewModel.getMovieList().observe(viewLifecycleOwner, ::onMovieListLoaded)
        viewModel.liveDataViewState_.observe(viewLifecycleOwner, ::setViewState)
    }

    private fun setViewState(fragmentViewState: MovieListFragmentViewState) {
        binding.viewState = fragmentViewState
        binding.executePendingBindings()
    }

    private fun onMovieListLoaded(movieList: PagedList<MovieViewItem>) {
        adapter.submitList(movieList)
    }

    private fun onMovieItemClick(movieViewItem: MovieViewItem) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                movieId = movieViewItem.id
            )
        )
    }
}