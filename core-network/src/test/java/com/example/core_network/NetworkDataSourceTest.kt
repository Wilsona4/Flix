package com.example.core_network

import com.example.core_network.model.PopularMovieDTO
import com.example.core_network.utils.MockApiServiceUtils.enqueueMockResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class NetworkDataSourceTest {

    private val mockWebServer by lazy {
        MockWebServer()
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun `should fetch movies given 200 response`() {

        runBlocking {
            // Prepare fake response
            mockWebServer.enqueueMockResponse("popular-movies-200.json", 200)

            val actual = apiService.getPopularMovies(1)

            assertThat(actual.results).isNotNull()
        }
    }


    @Test
    fun `should fetch movies correctly given 200 response`() {

        runBlocking {
            // Prepare fake response
            mockWebServer.enqueueMockResponse("popular-movies-200.json", 200)
            //Send Request to the MockServer
            val actual = apiService.getPopularMovies(1).results

            val expected =
                PopularMovieDTO(
                    adult = true,
                    backdrop_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                    genre_ids = listOf(1, 2, 4, 5),
                    id = 297761,
                    original_language = "en",
                    original_title = "Suicide Squad",
                    overview =
                    "From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains who act as deniable assets for the United States government, undertaking high-risk black ops missions in exchange for commuted prison sentences.",
                    popularity = 46.7,
                    poster_path = "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg",
                    release_date = "2016-08-03",
                    title = "Suicide Squad",
                    video = false,
                    vote_average = 5.8,
                    vote_count = 5193
                )

            assertThat(expected.id).isEqualTo(actual.first().id)
            assertThat(expected.title).isEqualTo(actual.first().title)
        }
    }

    @Test
    fun `should check valid url path`() {
        runBlocking {
            // Prepare fake response
            mockWebServer.enqueueMockResponse("popular-movies-200.json", 200)
            //Send Request to the MockServer
            val responseBody = apiService.getPopularMovies(1).results
            //Request received by the mock server
            val request = mockWebServer.takeRequest()

            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/movie/popular?page=1")
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}