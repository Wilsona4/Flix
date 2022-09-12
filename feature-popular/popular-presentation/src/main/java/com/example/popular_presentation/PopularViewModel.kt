package com.example.popular_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.popular_data.model.PopularMovie
import com.example.popular_data.repository.IPopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val popularRepository: IPopularRepository
) : ViewModel() {

    val popularMovies: Flow<PagingData<PopularMovie>> =
        popularRepository.getPopularMovies()
            .cachedIn(viewModelScope)
}
