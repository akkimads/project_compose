package com.example.assignment_compose.util

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment_compose.screens.EachRow
import com.example.assignment_compose.screens.EachRowForSearch
import com.example.assignment_compose.screens.EachRowSearch
import com.example.assignment_compose.viewModels.SearchViewModel
import com.example.assignment_compose.viewModels.TrendingViewModel

@Composable
fun SearchField(
    searchViewModel: SearchViewModel,
    navHostController: NavHostController
) {
    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = searchViewModel.responseS.value

    Surface(modifier = Modifier.height(60.dp).background(Color.Black)
    ) {

            TextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                    Log.d("ak search", it)
                    searchViewModel.getSearchDetails(query.value)

                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (query.value.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                // Remove text from TextField when you press the 'X' icon
                                query.value = ""
                                searchViewModel.getSearchDetails(query.value)

                            }
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }


                },

                label = { Text(text = "Search here...") },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                modifier = Modifier.fillMaxWidth(),
            )


    }

    when (result) {
        is NetworkResultS.Success -> {

            LazyColumn() {
                Log.d("Akash", result.dataSealed.results.size.toString())

                items(result.dataSealed.results) { response ->
                    EachRowForSearch(post = response, navHostController)
                }
            }


        }

        is NetworkResultS.Loading -> {
            androidx.compose.material3.CircularProgressIndicator()
        }

        is NetworkResultS.Empty -> {
            Log.d("akku", "empty ")

        }

        else -> {}
    }


}
