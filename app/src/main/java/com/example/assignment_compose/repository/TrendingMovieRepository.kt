package com.example.assignment_compose.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.assignment_compose.di.ApiInterface
import com.example.assignment_compose.models.movieListModel.MoviesDataBase
import com.example.assignment_compose.models.searchMovieModel.SearchMovies
import com.example.assignment_compose.util.NetworkBoundResource
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrendingMovieRepository @Inject constructor(private val apiInterface: ApiInterface) {
   val TAG = "Movie repository :"
/*
    private val moviesDao = dataBase.moviesDao()

    fun getSavedMovies() = NetworkBoundResource(
        query = {
            moviesDao.getAllMovies()
        },
        fetch = {
            delay(2000)
            apiInterface.getTrending()
        },
        saveFetchResult = { CarList ->
            dataBase.withTransaction {
                moviesDao.deleteMovies()
                moviesDao.insertMovies(CarList)
            }
        }
    )*/
    //search list of trending movies
    fun fetchData(): Flow<TrendingData> = flow {
        try {
            val response = apiInterface.getTrending()
            Log.d(TAG,response.toString())
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)

    // search movies
 fun fetchSearchedData(querys: String): Flow<SearchMovies> = flow {
        try {
            val response = apiInterface.searchMovies(querys)
            Log.d(TAG,response.toString())
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)


    // show similar data
    fun fetchSimilarData(id: String): Flow<TrendingData> = flow {
        try {
            val response = apiInterface.showSimilarMovies(id)
            Log.d(TAG,response.toString())
            emit(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.flowOn(Dispatchers.IO)



}