package com.example.assignment_compose.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment_compose.repository.TrendingMovieRepository
import com.example.assignment_compose.util.NetworkResultS

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: TrendingMovieRepository) :
    ViewModel() {
    val responseS: MutableState<NetworkResultS> = mutableStateOf(NetworkResultS.Empty)

    fun getSearchDetails(querys: String) =
        viewModelScope.launch {
            repository.fetchSearchedData(querys).onStart {

                responseS.value = NetworkResultS.Loading
                Log.d("Akash", responseS.value.toString())
            }.catch {
                responseS.value = NetworkResultS.Failure(it)
            }.collect {
                responseS.value = NetworkResultS.Success(it)
                Log.d(" search success", responseS.value.toString())

            }
        }

}