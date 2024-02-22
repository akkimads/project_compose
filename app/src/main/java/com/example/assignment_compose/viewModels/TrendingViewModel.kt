package com.example.assignment_compose.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmenttmdb.models.movieListModel.Result

import com.example.assignment_compose.repository.TrendingMovieRepository
import com.example.assignment_compose.util.NetworkResult
import com.example.assignmenttmdb.models.movieListModel.ApiResponse
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel
@Inject constructor(private val repository: TrendingMovieRepository): ViewModel() {
/*

    val user: Flow<TrendingData> = repository.fetchData().map { it.data }

    val posts: Flow<List<Result>> = repository.fetchData().map { it.result }
*/

//    val responseDb = repository.getSavedMovies().asLiveData()


    val response: MutableState<NetworkResult> = mutableStateOf(NetworkResult.Empty)


    init {
        getMovieDetails()
    }

    fun getMovieDetails() =
        viewModelScope.launch {

            repository.fetchData().onStart {

                response.value= NetworkResult.Loading
                Log.d("Akash", response.value.toString())
            }.catch {
                response.value= NetworkResult.Failure(it)
            }.collect {
                response.value=NetworkResult.Success(it)
                Log.d("Akash success", response.value.toString())

            }
            }

    }




