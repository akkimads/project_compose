package com.example.assignment_compose.dataSource

import com.example.assignment_compose.di.ApiInterface
import com.example.assignmenttmdb.models.movieListModel.ApiResponse
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import retrofit2.Response
import javax.inject.Inject

class TrendingDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface) :
    TrendingDataSource {
    override suspend fun getTrending(): TrendingData {
        return apiInterface.getTrending()
    }
}