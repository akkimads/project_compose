package com.example.assignment_compose.util

sealed class NavRoutes(val routes: String) {


    object MovieScreen : NavRoutes("Home")
    object DetailScreen : NavRoutes("Detail")
}