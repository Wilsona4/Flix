package com.example.core_database.mediator

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core_database.FakeMovies.movie1
import com.example.core_database.FakeMovies.movie2
import com.example.core_database.FakeMovies.movie3
import com.example.core_database.FlixDatabase
import com.example.core_database.model.PopularMovieEntity
import com.example.core_network.ApiService
import com.example.core_network.model.PopularResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class RemoteMediatorTest {

    private lateinit var mockDb: FlixDatabase
    private var mockApi: ApiService = mockk()
    private val mockMovies = listOf(
        movie1, movie2, movie3
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        mockDb = Room.inMemoryDatabaseBuilder(
            context, FlixDatabase::class.java
        ).build()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runTest {
        // Add mock results for the API to return.
        coEvery { mockApi.getPopularMovies(1) } returns PopularResponse(
            page = 1,
            results = mockMovies,
            2,
            2
        )

        val remoteMediator = PopularRemoteMediator(
            mockApi,
            mockDb
        )
        val pagingState = PagingState<Int, PopularMovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
//        assertTrue { result is RemoteMediator.MediatorResult.Success }
//        assertFalse { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runTest {
        // To test endOfPaginationReached, set up the mockApi to return empty data here.
        coEvery { mockApi.getPopularMovies(1) } returns PopularResponse(
            page = 3,
            results = emptyList(),
            3,
            3
        )
        val remoteMediator = PopularRemoteMediator(
            mockApi,
            mockDb
        )
        val pagingState = PagingState<Int, PopularMovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
//        assertTrue { result is RemoteMediator.MediatorResult.Success }
//        assertTrue { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() = runTest {
        // Set up failure message to throw exception from the mock API.
        coEvery { mockApi.getPopularMovies(1) } throws IOException("Throw test failure")
        val remoteMediator = PopularRemoteMediator(
            mockApi,
            mockDb
        )
        val pagingState = PagingState<Int, PopularMovieEntity>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
//        assertTrue { result is RemoteMediator.MediatorResult.Error }
    }

    @After
    fun tearDown() {
        mockDb.clearAllTables()
        mockDb.close()
    }

}