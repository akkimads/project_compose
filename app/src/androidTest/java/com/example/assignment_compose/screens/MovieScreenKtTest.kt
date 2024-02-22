package com.example.assignment_compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.dx.command.Main
import com.example.assignment_compose.CounterDisplay
import com.example.assignment_compose.MainActivity
import com.example.assignment_compose.ui.theme.Assignment_composeTheme
import com.example.assignment_compose.viewModels.SearchViewModel
import com.example.assignment_compose.viewModels.TrendingViewModel/*
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest*/
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@HiltAndroidTest
class MovieScreenKtTest {

    @get: Rule
    val composeTestRule = createComposeRule()   // compose rule is required to get access to the composable component
    @Before
    fun setUp() {
        composeTestRule.setContent {    // setting our composable as content for test
            Assignment_composeTheme {
                CounterDisplay(modifier = Modifier.fillMaxSize().background(Color.Black))
            }
        }
    }
    @Test
    fun verify_if_all_views_exists() {
        composeTestRule.onNodeWithTag("Counter Display").assertExists()
        composeTestRule.onNodeWithTag("Input").assertExists()
        composeTestRule.onNodeWithText("Copy").performClick()
        composeTestRule.onNodeWithText("Copy").performClick()
    }
    /*

val context = ApplicationProvider.getApplicationContext<MainActivity>()
    *//*@get: Rule
    val composeTestRule =
        createAndroidComposeRule<MainActivity>()
*//*
    lateinit var trendingViewmodel: TrendingViewModel
    lateinit var searchViewModel: SearchViewModel


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    @OptIn(ExperimentalMaterialNavigationApi::class)
    @Before
    fun setUp() {
//        hiltRule.inject()
        composeTestRule.setContent {    // setting our composable as content for test
            Assignment_composeTheme {
               // MovieScreen(navController,trendingViewmodel, searchViewModel)
            }
        }
    }
*//*
    @Test
    fun dDisplay() {
        composeTestRule.onNodeWithText("sea").performClick()

    }*//*
*/
}