package com.example.popular_data.repository

import androidx.paging.PagingData
import com.example.popular_data.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface IPopularRepository {
    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
}