package com.example.assignment_compose.di

import com.example.assignment_compose.constants.Constant
import com.example.assignment_compose.models.searchMovieModel.SearchMovies
import com.example.assignment_compose.models.searchMovieModel.SearchResult
import com.example.assignmenttmdb.models.movieListModel.ApiResponse
import com.example.assignmenttmdb.models.movieListModel.Result
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"

    }
    @GET(Constant.END_POINT_TRENDING)
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzkxN2ZjMWIxMTgxNTY4NDJiMzYyN2ZmODEwY2Q4YyIsInN1YiI6IjVhYTAxZWQzMGUwYTI2MzI3YTAxMGEzMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W5PptCGyk0S9lHOADzCZR2dD1ZrKHspNlSLrmvsexGE")
    suspend fun getTrending() : TrendingData


    @GET("search/tv")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzkxN2ZjMWIxMTgxNTY4NDJiMzYyN2ZmODEwY2Q4YyIsInN1YiI6IjVhYTAxZWQzMGUwYTI2MzI3YTAxMGEzMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W5PptCGyk0S9lHOADzCZR2dD1ZrKHspNlSLrmvsexGE")
    suspend fun searchMovies(@Query("query") key: String) : SearchMovies

    @GET("movie/{path}/similar?language=en-US&page=1")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzkxN2ZjMWIxMTgxNTY4NDJiMzYyN2ZmODEwY2Q4YyIsInN1YiI6IjVhYTAxZWQzMGUwYTI2MzI3YTAxMGEzMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W5PptCGyk0S9lHOADzCZR2dD1ZrKHspNlSLrmvsexGE")
    suspend fun showSimilarMovies(@Path("path")key: String) : TrendingData

}