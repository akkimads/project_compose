package com.example.assignment_compose.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.assignment_compose.R
import com.example.assignment_compose.constants.Constant.BASE_URL_IMAGE
import com.example.assignment_compose.ui.theme.Grey
import com.example.assignment_compose.util.NavRoutes
import com.example.assignment_compose.util.NetworkResult
import com.example.assignment_compose.util.RatingBar
import com.example.assignment_compose.util.SearchField
import com.example.assignment_compose.viewModels.SearchViewModel
import com.example.assignment_compose.viewModels.TrendingViewModel
import com.example.assignmenttmdb.models.movieListModel.Result
import com.example.assignment_compose.models.searchMovieModel.SearchResult

import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun MovieScreen(
    navHostController: NavHostController,
    trendingViewModel: TrendingViewModel,
    searchViewModel: SearchViewModel
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {

            Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {
                SearchField(searchViewModel, navHostController)
            }


        when (val result = trendingViewModel.response.value) {
            is NetworkResult.Success -> {


                LazyColumn(modifier = Modifier.padding(20.dp)) {
                    Log.d("Akash", result.dataSealed.results.size.toString())

                    items(result.dataSealed.results) { response ->
                        EachRow(post = response, navHostController)
                    }
                }

                Log.d("akku", "success")
            }

            is NetworkResult.Failure -> {
                Text(text = "${result.msg}")
                Log.d("akku", "fail ")

            }

            NetworkResult.Loading -> {
                CircularProgressIndicator()
                Log.d("akku", "load ")

            }

            NetworkResult.Empty -> {
                Log.d("akku", "empty ")

            }
        }
    }
}
/*
@Composable
fun PostItem(post: Result) {
    // Display post details
    Text(text = "Title: ${post.title}")
    Text(text = "Body: ${post.original_name}")
    Spacer(modifier = Modifier.height(16.dp))
}



*/

@Composable
fun EachRow(post: Result, navHostController: NavHostController) {
    Log.d("Akash row", BASE_URL_IMAGE.plus(post?.backdrop_path))
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.FLOOR

    val rating = String.format("%.1f", post.vote_average)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .height(200.dp)
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .background(Color.Black)
                .clickable { navHostController.navigate(NavRoutes.DetailScreen.routes + "/${post.original_title ?: post.original_name}${post.backdrop_path}/${post.overview}/${post.vote_average}/${post.id}") },
            elevation = 5.dp,

            shape = RoundedCornerShape(24.dp)
        ) {

            val matrix = ColorMatrix()
            matrix.setToSaturation(1F)
            AsyncImage(
                model = BASE_URL_IMAGE.plus(post?.backdrop_path),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "The delasign logo",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .background(color = Color.Black)
            )


            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = post.original_title ?: post.original_name,
                    modifier = Modifier
                        .padding(0.dp, 60.dp, 0.dp, 30.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )

                RatingBar(currentRating = rating.toDouble())
                Text(
                    text = rating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },

                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }

        }


    }

}

@Composable
fun EachRowForSearch(post: SearchResult, navHostController: NavHostController) {
    Log.d("Akash row", BASE_URL_IMAGE.plus(post?.backdrop_path))
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.FLOOR

    val rating = String.format("%.1f", post.vote_average)

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.Black)
    ) {
        Card(
            modifier = Modifier
                .width(320.dp)
                .height(200.dp)
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .background(Color.Black)
                .clickable { navHostController.navigate(NavRoutes.DetailScreen.routes + "/${post.name ?: post.original_name}${post.backdrop_path}/${post.overview}/${post.vote_average}/${post.id}") },
            elevation = 5.dp,

            shape = RoundedCornerShape(24.dp)
        ) {

            val matrix = ColorMatrix()
            matrix.setToSaturation(1F)
            AsyncImage(
                model = BASE_URL_IMAGE.plus(post?.backdrop_path),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "The delasign logo",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .background(color = Color.Black)
            )


            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = post.name ?: post.original_name,
                    modifier = Modifier
                        .padding(0.dp, 60.dp, 0.dp, 30.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )

                RatingBar(currentRating = rating.toDouble())
                Text(
                    text = rating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },

                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )
            }

        }


    }

}

@Composable
fun EachRowSimilar(post: Result, navHostController: NavHostController) {
    Log.d("Akash row", BASE_URL_IMAGE.plus(post?.backdrop_path))
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.FLOOR

    val rating = String.format("%.1f", post.vote_average)

    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp
            )
            .clickable { navHostController.navigate(NavRoutes.DetailScreen.routes + "/${post.original_title ?: post.original_name}${post.backdrop_path}/${post.overview}/${post.vote_average}/${post.id}") },
        elevation = 5.dp,

        shape = RoundedCornerShape(24.dp)
    ) {

        val matrix = ColorMatrix()
        matrix.setToSaturation(1F)
        AsyncImage(
            model = BASE_URL_IMAGE.plus(post?.backdrop_path) ?: error("Image not found"),
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "The delasign logo",
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .padding(1.dp)
                .background(color = Color.Black)
                .width(100.dp)
                .height(100.dp)
        )



        Text(
            text = post.original_title ?: post.original_name,
            modifier = Modifier
                .fillMaxWidth(),
            style = TextStyle(
                color = Color.White,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace
            )
        )


    }

}


@Composable
fun EachRowSearch(post: SearchResult, navHostController: NavHostController) {
    Log.d("Akash detail search", BASE_URL_IMAGE.plus(post?.backdrop_path))
    Log.d("Akash detail search", post.name)
    Log.d("Akash detail search", post.vote_average.toString())
    Log.d("Akash detail search", post.overview)
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
                .clickable { navHostController.navigate(NavRoutes.DetailScreen.routes + "/${post?.name ?: post.original_name}${post?.backdrop_path}/${post.overview ?: post.name}/${post?.vote_average ?: "no vote"}") },
            elevation = 5.dp,

            shape = RoundedCornerShape(24.dp)
        ) {

            val matrix = ColorMatrix()
            matrix.setToSaturation(1F)
            AsyncImage(
                model = BASE_URL_IMAGE.plus(post?.backdrop_path),
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "The delasign logo",
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .padding(1.dp)
                    .background(color = Color.Black)
            )


            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = post.name ?: post.original_name,
                    modifier = Modifier
                        .padding(60.dp)
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },

                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.FLOOR

                val rating = String.format("%.1f", post.vote_average)

                RatingBar(currentRating = rating.toDouble())
                Text(
                    text = rating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawBehind {
                            drawRect(
                                color = Grey, size = size
                            )
                        },

                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Monospace
                    )
                )

            }

        }


    }

}




