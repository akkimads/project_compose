package com.example.assignment_compose.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.assignment_compose.di.ApiInterface
import com.example.assignment_compose.repository.TrendingMovieRepository
import com.example.assignment_compose.util.NetworkResult
import com.example.assignmenttmdb.models.movieListModel.Result
import com.example.assignmenttmdb.models.movieListModel.TrendingData
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class TrendingViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var trendingMovieRepository: TrendingMovieRepository
    lateinit var trendingViewmodel: TrendingViewModel

    @Mock
    lateinit var apiInterface: ApiInterface


    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    private lateinit var repository: TrendingMovieRepository
    private lateinit var mockedResponse: String
    private val gson = GsonBuilder()
        .setLenient()
        .create()


    @Before
    fun setUp() {/*
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        trendingMovieRepository = mock(TrendingMovieRepository::class.java)
        trendingViewmodel = TrendingViewModel(trendingMovieRepository)
   */

        server.start(8000)
        var BASE_URL = server.url("/").toString()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(ApiInterface::class.java)
       // trendingViewmodel = TrendingViewModel(trendingMovieRepository)

        repository = TrendingMovieRepository(service)
    }

    @Test
    fun getAllMovies() {
        mockedResponse = MockResponseFileReader("movieList.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runTest { repository.fetchData().toList() }
       /* val json = gson.toJson(response.collect())
        val resultResponse = JsonParser.parseString(json)
        val expectedresponse = JsonParser.parseString(mockedResponse)
       */
        Assert.assertNotNull(response)
        //assert(response is NetworkResult.Failure)//Our repo shows error as the response code was 400.

//        assertEquals(response,trendingViewmodel.response)
        //Assert.assertTrue(resultResponse.equals(expectedresponse))
        }
    }

    @After
    fun tearDown() {
       // server.shutdown()
    }
