package com.example.assignment_compose.models.searchMovieModel

data class SearchMovies(
    val page: Int,
    val results: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)