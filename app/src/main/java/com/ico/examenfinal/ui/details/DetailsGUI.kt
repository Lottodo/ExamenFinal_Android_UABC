package com.ico.examenfinal.ui.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ico.myapplication.data.api.model.Show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsGUI(show: Show) {
    val activity = (LocalContext.current as? Activity)
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant

        ) {
            IconButton(onClick = {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "${show.name} on TV Maze: ${show.url}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Descripción del icono"
                )
            }
        }
    }
    Column {
        TopAppBar(title = {
            IconButton(onClick = { activity?.finish() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Botón de retroceso"
                )
            }
        })
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            Row {
                ElevatedCard(
                    modifier = Modifier
                        .size(width = 175.dp, height = 250.dp)
                        .padding(12.dp),
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(),
                    ) {
                        Box(
                            contentAlignment = Alignment.TopStart,
                        ) {
                            AsyncImage(
                                model = show.image.original,
                                contentDescription = "Poster del show",
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(1.dp))
                            ) {
                                Text(
                                    text = show.rating.average.toString(),
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.surfaceVariant)
                                        .padding(2.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = show.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    val modifier = Modifier.padding(vertical = 4.dp)

                    Text(
                        text = boldTitleRegularText("Genres: ", getGenresString(show)), modifier = modifier)
                    Text(text = boldTitleRegularText("Premiered: ", show.premiered), modifier = modifier)
                    Text(text = boldTitleRegularText("Country: ", "${show.network.country.name}, ${show.network.country.code}"), modifier = modifier)
                    Text(text = boldTitleRegularText("Language: ", show.language), modifier = modifier)
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "Summary",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = show.summary.replace("<br>","\n").replace(Regex("<.*?>"), ""),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )
            }

        }
    }
}

fun boldTitleRegularText(title: String, regular: String): AnnotatedString {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(title)
        }
        append(regular)
    }

    return text
}

fun getGenresString(show: Show): String {
    var genresString = ""
    for ((index, item) in show.genres.withIndex()) {
        if (index == show.genres.size - 1) {
            genresString += item
        }
        else
            genresString = "$genresString$item, "
    }

    return genresString
}

/*
@Composable
fun DetailsGUI(show: Show) {
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxWidth(),
    ) {

        ImageWithIconButtons(
            context = context,
            show = show
        )

        TitleWithIconButton(
            show = show
        )

        ShowDetails(
            show = show,
            modifier = Modifier
                .padding(3.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
//Contenedor imagen, back y share
fun ImageWithIconButtons(context: Context, show: Show) {
    val activity = (LocalContext.current as? Activity)
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart,
    ) {

        //Contenedor de imagen
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = show.image.original,
                contentDescription = "Poster del show",
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        //Contenedor de boton back y share
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            //Back
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(all = 8.dp),

                ) {
                IconButton(
                    onClick = { activity?.finish() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                }
            }

            //Share
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(all = 8.dp),

                ) {
                IconButton(
                    onClick = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Check out the ${show.name} show in the Android Sunflower app")
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartir",
                    )
                }
            }

        }


    }
}

@Composable
//Nombre de fruta y icono de +
fun TitleWithIconButton(show: Show) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = show.name,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
        }

        Surface(
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            color = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.padding(all = 8.dp)
        ) {
            IconButton(
                onClick = { /*AddShowToGarden(context, show)*/ }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Favorito")
            }
        }
    }
}

@Composable
//Watering needs y descripcion
fun ShowDetails(show: Show, modifier: Modifier) {
    //Necesidades de watering y descripcion
    Text(
        text = "Pending",
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = modifier,
    )
    Text(
        text = "Pending",
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        modifier = modifier
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = show.summary.replace("<br>","\n").replace(Regex("<.*?>"), ""),
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}*/