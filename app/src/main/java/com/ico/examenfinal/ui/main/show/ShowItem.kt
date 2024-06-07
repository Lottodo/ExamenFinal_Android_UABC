package com.ico.examenfinal.ui.main.show

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ico.examenfinal.ui.details.ShowActivity
import com.ico.myapplication.data.api.model.Show

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowItem(show: Show) {
    val context = LocalContext.current

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        ),
        modifier = Modifier
            .size(width = 130.dp, height = 250.dp)
            .padding(5.dp),
        onClick = {
            val intent = Intent(context, ShowActivity::class.java)
            intent.putExtra("show", show)
            context.startActivity(intent)
        }
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier.size(180.dp)
            ) {
                AsyncImage(
                    model = show.image.medium,
                    contentDescription = "Poster del show",
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(1.dp))

                ) {
                    Text(
                        text = show.rating.average.toString(),
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.onBackground)
                            .padding(2.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.background
                    )
                }

            }


            Text(text = show.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(lineHeight = 20.sp)
            )

            //toString de los genres
            var genresString = ""
            for ((index, item) in show.genres.withIndex()) {
                if (index == show.genres.size - 1) {
                    genresString += item
                }
                else
                    genresString = "$genresString$item, "
            }
            Text(text = genresString,
                fontSize = 10.sp,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(lineHeight = 20.sp)
            )
        }
    }

}