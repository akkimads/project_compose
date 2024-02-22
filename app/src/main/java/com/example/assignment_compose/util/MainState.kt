package com.example.assignment_compose.util

import com.example.assignment_compose.models.searchMovieModel.SearchMovies

data class MainState(
    val isLoading:Boolean=false,
    val data: SearchMovies,
val error:String=""
)