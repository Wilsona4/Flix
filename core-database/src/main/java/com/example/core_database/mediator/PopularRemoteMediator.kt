package com.example.core_database.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.core_database.FlixDatabase
import com.example.core_database.model.PopularMovieEntity
import com.example.core_database.model.RemoteKeys
import com.example.core_database.model.asEntity
import com.example.core_network.ApiService
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class PopularRemoteMediator(
    private val apiService: ApiService,
    private val flixDatabase: FlixDatabase
) : RemoteMediator<Int, PopularMovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        // Require that remote REFRESH is launched on initial load and succeeds before launching
        // remote PREPEND / APPEND.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val pageKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: MOVIES_STARTING_PAGE_INDEX
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    // If remoteKeys is null, that means the refresh result is not in the database yet.
                    // We can return Success with `endOfPaginationReached = false` because Paging
                    // will call this method again if RemoteKeys becomes non-null.

                    // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                    // the end of pagination for prepend.
                    val prevKey = remoteKeys?.prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevKey
                }

                LoadType.APPEND -> {

                    val remoteKeys = getRemoteKeyForLastItem(state)

                    // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                    // the end of pagination for append.
                    val nextKey = remoteKeys?.nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                    nextKey
                }
            }

            val apiResponse = apiService.getPopularMovies(page = pageKey)

            val movies = apiResponse.results

            val endOfPaginationReached = movies.isEmpty()

            flixDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    flixDatabase.remoteKeysDao().clearRemoteKeys()
                    flixDatabase.popularDao().clearMovies()
                }
                val prevKey = if (pageKey == MOVIES_STARTING_PAGE_INDEX) null else pageKey.minus(1)
                val nextKey = if (endOfPaginationReached) null else pageKey.plus(1)

                val keys = movies.map {
                    RemoteKeys(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                flixDatabase.remoteKeysDao().insertAll(keys)
                flixDatabase.popularDao().insertAllMovies(movies.map { it.asEntity() })
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularMovieEntity>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { user ->
                // Get the remote keys of the last item retrieved
                flixDatabase.remoteKeysDao().remoteKeysRepoId(user.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularMovieEntity>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                // Get the remote keys of the first items retrieved
                flixDatabase.remoteKeysDao().remoteKeysRepoId(user.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PopularMovieEntity>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { userId ->
                flixDatabase.remoteKeysDao().remoteKeysRepoId(userId)
            }
        }
    }

    companion object {
        private const val MOVIES_STARTING_PAGE_INDEX = 1
    }
}