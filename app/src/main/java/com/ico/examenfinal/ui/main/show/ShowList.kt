package com.ico.examenfinal.ui.main.show

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ico.myapplication.data.api.model.Show

@Composable
fun ShowList(showsList: List<Show>) {
    val tabList = listOf("Top 50", "My favorites")
    var tabIndex by remember{ mutableStateOf(0) }
    
    Column() {
        //Titulo
        Text(
            text = "Tv Maze",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        )
        
        //Pestañas
        TabRow(selectedTabIndex = tabIndex) {
            tabList.forEachIndexed { index, title ->  
                Tab(
                    text = { Text(text = title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        
        //Contenido de pestanas
        when (tabIndex) {
            0 -> ShowsTab(showsList)
            1 -> FavTab()
        }
    }
}

@Composable
fun ShowsTab(showsList: List<Show>) {
    val sortedShowsList = showsList.sortedByDescending { show ->
        show.rating.average ?: 0.0
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(sortedShowsList.take(50).chunked(3)) { rowItems ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (show in rowItems) {
                    ShowItem(show = show)//, context = context)
                }
            }
        }

    }
}

@Composable
fun FavTab() {
    Text(text = "waos")
}