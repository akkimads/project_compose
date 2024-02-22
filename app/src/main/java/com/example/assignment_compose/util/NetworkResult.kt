package com.example.assignment_compose.util

import com.example.assignmenttmdb.models.movieListModel.TrendingData

sealed class NetworkResult {
    class Success(val dataSealed: TrendingData) : NetworkResult()
    class Failure(val msg: Throwable) : NetworkResult()
    object Loading:NetworkResult()
    object Empty: NetworkResult()
    }