package com.example.assignment_compose.dataSource

import com.example.assignmenttmdb.models.movieListModel.ApiResponse
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import retrofit2.Response

interface TrendingDataSource {
    suspend fun getTrending() : TrendingData

}