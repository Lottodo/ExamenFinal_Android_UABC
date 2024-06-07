package com.ico.myapplication.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ico.myapplication.data.api.model.Show

/*
@Composable
fun HomeScreen() {
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by mainViewModel.state.collectAsState()

    LazyColumn {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )

            }
        }

        items(state) { show  ->
            ShowImageCard(show)
        }

    }
}

/**
 * Using Coil to load images
 */
@Composable
fun ShowImageCard(show: Show) {
    val imagePainter = rememberAsyncImagePainter(model = show.image.medium)

    Card(
//        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(size = 30.dp)


    ) {
        Box{

            Image(painter = imagePainter, contentDescription = null,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )


            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomEnd),


                contentColor = MaterialTheme.colorScheme.onSurface

            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)



                ) {


                    Text(
                        text = "Real Name: ${show.name} ",
                        Modifier.align(Alignment.End),
                        style = MaterialTheme.typography.bodyLarge


                    )


                    Text(
                        text = "Actor Name: ${show.url} ",
                        Modifier.align(Alignment.End),
                        style = MaterialTheme.typography.bodySmall

                    )



                }


            }




        }

    }
}*/