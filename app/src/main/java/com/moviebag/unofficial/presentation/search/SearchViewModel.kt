package com.moviebag.unofficial.presentation.search

import androidx.databinding.ObservableField
import com.moviebag.unofficial.base.viewmodel.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel() {
    var query = ObservableField("")
}