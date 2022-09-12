package com.example.core_network.model

data class PopularResponse(
    val page: Int,
    val results: List<PopularMovieDTO>,
    val total_pages: Int,
    val total_results: Int
)