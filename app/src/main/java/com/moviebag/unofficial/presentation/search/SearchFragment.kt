/*
package com.moviebag.unofficial.presentation.search

import android.graphics.Color
import android.transition.TransitionInflater
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.moviebag.unofficial.base.view.BaseFragment
import com.moviebag.unofficial.data.enum.MovieListPageType
import com.moviebag.unofficial.R
import com.moviebag.unofficial.base.extension.clickSubmitButton
import com.moviebag.unofficial.base.extension.hideKeyboard
import com.moviebag.unofficial.base.extension.showKeyboard
import com.moviebag.unofficial.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_search
    override val classTypeOfViewModel: Class<SearchViewModel> = SearchViewModel::class.java

    override fun init() {
        context?.let { sharedElementEnterTransition = TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
        setupSearchView()
    }

    override fun setupClickListeners() {
        binding.imageButtonBack.setOnClickListener { findNavController().popBackStack() }
    }

    private fun setupSearchView() {
        (binding.searchView.findViewById(androidx.appcompat.R.id.search_src_text) as TextView).setTextColor(Color.WHITE)
        binding.searchView.clickSubmitButton { query ->
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieListFragment(
                    pageType = MovieListPageType.SEARCH,
                    searchQuery = query
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()
        binding.searchView.showKeyboard(requireActivity())
        binding.searchView.onActionViewExpanded()
    }

    override fun onStop() {
        super.onStop()
        binding.searchView.hideKeyboard(requireActivity())
    }
}
*/
