package com.example.assignment_compose.ui.appComponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Loader(){
    CircularProgressIndicator(
        modifier = Modifier.size(50.dp).padding(10.dp),
        color = Color.Black
    )
}