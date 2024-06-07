package com.ico.examenfinal.ui.details

import android.app.Activity
import android.content.Intent
import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ico.examenfinal.data.api.model.Cast
import com.ico.examenfinal.data.api.model.CastShow
import com.ico.myapplication.data.api.model.Show

@Composable
fun DetailsScreen(show: Show, cast: Cast?) {
    val activity = (LocalContext.current as? Activity)
    val context = LocalContext.current
    var text by remember { mutableStateOf(" ") }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            IconButton(onClick = { activity?.finish() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button",
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(
                onClick = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "${show.name} on TV Maze: ${show.url}")
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)
                    context.startActivity(shareIntent)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite button",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
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
                            if (show.image == null)
                                Log.d("WAOS",show.name)
                            else
                            AsyncImage(
                                model = show.image.original,
                                contentDescription = "Show's image",
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(1.dp))
                            ) {
                                var rating = show.rating.average.toString()
                                if (rating == "null") rating = "N/A"
                                Text(
                                    text = rating,
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.onBackground)
                                        .padding(2.dp),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.background
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

                    Text(text = boldTitleRegularText("Genres: ", getGenresString(show)), modifier = modifier)
                    Text(text = boldTitleRegularText("Premiered: ", formatedDate(show.premiered)), modifier = modifier)

                    if (show.network == null)
                        Text(text = boldTitleRegularText("Country: ", "N/A"), modifier = modifier)
                    else
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
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .height(200.dp)
                ) {
                    Text(
                        text = show.summary.replace("<br>", "\n").replace(Regex("<.*?>"), ""),
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 5.dp)
                            .verticalScroll(rememberScrollState())

                    )
                }
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = "Cast",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
                if (cast == null)
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 5.dp)
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                    )
                else {
                    LazyColumn {
                        items(cast._embedded.cast) {castShow ->
                            CastItem(castShow = castShow)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CastItem(castShow: CastShow) {
    Row(
        modifier = Modifier
            .padding(16.dp,8.dp),
    ) {
        Card {
            if (castShow.person.image == null)
                Log.d("WAOS",castShow.person.name)
            else
            AsyncImage(
                model = castShow.person.image.medium,
                contentDescription = "Actor photo",
                modifier = Modifier
                    .height(100.dp),
                contentScale = ContentScale.FillHeight
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column{
            val modifier = Modifier.padding(vertical = 4.dp)
            Text(
                text = boldTitleRegularText("Name: ", castShow.person.name),
                modifier = modifier,
                fontSize = 20.sp
            )
            Text(text = boldTitleRegularText("Role: ", castShow.character.name),
                modifier = modifier,
                fontSize = 20.sp
            )
        }
    }
}









