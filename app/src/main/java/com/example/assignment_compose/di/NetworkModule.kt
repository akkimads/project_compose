package com.example.assignment_compose.di

import androidx.room.Room
import com.example.assignment_compose.MovieApplication
import com.example.assignment_compose.constants.Constant.BASE_URL
import com.example.assignment_compose.dataSource.TrendingDataSource
import com.example.assignment_compose.dataSource.TrendingDataSourceImpl
import com.example.assignment_compose.models.movieListModel.MoviesDataBase
import com.example.assignment_compose.repository.TrendingMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideClient() : OkHttpClient {
        val logginInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                HttpLoggingInterceptor.Level.BODY
                builder.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzkxN2ZjMWIxMTgxNTY4NDJiMzYyN2ZmODEwY2Q4YyIsInN1YiI6IjVhYTAxZWQzMGUwYTI2MzI3YTAxMGEzMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.W5PptCGyk0S9lHOADzCZR2dD1ZrKHspNlSLrmvsexGE")
                return@Interceptor chain.proceed(builder.build())
            })
            .addInterceptor(logginInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
    }

    @Singleton
    @Provides
    fun movieApi(retrofit : Retrofit) : ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(movieApplication: MovieApplication) : MoviesDataBase =
        Room.databaseBuilder(movieApplication, MoviesDataBase::class.java, "movies_database").build()


    /*@Singleton
    @Provides
    fun providesTrendingDataSource(apiInterface: ApiInterface) : TrendingDataSource{
        return TrendingDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun providesTrendingMovieRepository(dataSource: TrendingDataSource): TrendingMovieRepository{
        return TrendingMovieRepository(dataSource)
    }*/
}
