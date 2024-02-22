package com.example.assignment_compose.models.movieListModel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesList: TrendingData)

//    @Query("Delete from movies_dabba ")
    suspend fun deleteMovies()

//    @Query("Select * from movies_dabba")
     fun getAllMovies() : Flow<TrendingData>
}