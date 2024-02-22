package com.example.assignment_compose.util

import com.example.assignment_compose.models.searchMovieModel.SearchMovies
import com.example.assignmenttmdb.models.movieListModel.TrendingData


sealed class NetworkResultS {
    class Success(val dataSealed: SearchMovies) : NetworkResultS()
    class Failure(val msg: Throwable) : NetworkResultS()
    object Loading:NetworkResultS()
    object Empty: NetworkResultS()
}