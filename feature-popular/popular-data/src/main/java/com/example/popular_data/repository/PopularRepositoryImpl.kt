package com.example.popular_data.repository

import androidx.paging.*
import com.example.core_database.FlixDatabase
import com.example.core_database.dao.PopularDao
import com.example.core_database.mediator.PopularRemoteMediator
import com.example.core_network.ApiService
import com.example.popular_data.model.PopularMovie
import com.example.popular_data.model.asDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PopularRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val popularDao: PopularDao,
    private val flixDatabase: FlixDatabase
) : IPopularRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<PopularMovie>> = Pager(
        config = PagingConfig(NETWORK_PAGE_SIZE),
        remoteMediator = PopularRemoteMediator(apiService, flixDatabase)
    ) {
        popularDao.readMovies()
    }.flow
        .map { pagingData ->
            pagingData.map { popularEntity ->
                popularEntity.asDomainModel()
            }
        }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}

