package com.ico.examenfinal.ui.main

import android.annotation.SuppressLint
import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ico.examenfinal.data.api.model.Cast
import com.ico.examenfinal.data.api.model.ShowSearch
import com.ico.myapplication.data.api.ShowApi
import com.ico.myapplication.data.api.model.Show
import kotlinx.coroutines.launch

@Composable
fun MainScreen(showsList: List<Show>) {
    val tabList = listOf("Top 50 TV shows", "My favorites")
    var tabIndex by remember{ mutableStateOf(0) }


    
    Column() {
        //Titulo
        Text(
            text = "Tv Maze",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Light,
            fontSize = 22.sp,
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowsTab(showsList: List<Show>) {
    val sortedShowsList = showsList.sortedByDescending { show ->
        show.rating.average ?: 0.0
    }
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var searchResponse:List<ShowSearch> by remember { mutableStateOf(listOf()) }

    Column(
        Modifier.fillMaxSize()
    ) {
        SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                query = text,
                onQueryChange = {
                    text = it

                    coroutineScope.launch {
                        isLoading = true
                        val apiService = ShowApi.getInstance()
                        try {
                            val showSList = apiService.getShowsSearch(text)//text.replace(" ","%20"))
                            searchResponse = showSList
                        }
                        catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }
                        isLoading = false
                    }
                },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text(text = "Search more TV shows") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search")
                },
                trailingIcon = {
                    if (active)
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty())
                                    text = ""
                                else
                                    active = false
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Search")
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(searchResponse.chunked(3)) { rowItems ->
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (showS in rowItems) {
                                ShowItem(show = showS.show)//, context = context)
                            }
                        }
                    }
                }
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

}

@Composable
fun FavTab() {
    Text(text = "waos")
}