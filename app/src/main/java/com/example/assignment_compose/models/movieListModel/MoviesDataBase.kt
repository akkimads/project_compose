package com.example.assignment_compose.models.movieListModel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignmenttmdb.models.movieListModel.TrendingData

//@Database(entities = [TrendingData::class], version = 1)
abstract class MoviesDataBase : RoomDatabase() {

    abstract fun moviesDao() : MoviesDao
}