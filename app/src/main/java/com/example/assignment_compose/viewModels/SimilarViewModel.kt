package com.example.assignment_compose.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.assignment_compose.repository.TrendingMovieRepository
import com.example.assignment_compose.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel
@Inject constructor(private val repository: TrendingMovieRepository): ViewModel() {

    val responseSim: MutableState<NetworkResult> = mutableStateOf(NetworkResult.Empty)


    fun getSimilarDataDetails(id: String?) =
        viewModelScope.launch {
            repository.fetchSimilarData(id!!).onStart {

                responseSim.value= NetworkResult.Loading
                Log.d("Akash", responseSim.value.toString())
            }.catch {
                responseSim.value= NetworkResult.Failure(it)
            }.collect {
                responseSim.value=NetworkResult.Success(it)
                Log.d("Akash success", responseSim.value.toString())

            }
        }

}




