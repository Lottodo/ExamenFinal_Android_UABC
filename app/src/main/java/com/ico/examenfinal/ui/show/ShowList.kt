package com.ico.examenfinal.ui.show

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.ico.examenfinal.ui.home.ShowItem
import com.ico.myapplication.data.api.model.Show

@Composable
fun ShowList(showList: List<Show>) {
    LazyColumn {
        itemsIndexed(items = showList.take(50)) { index, item ->
            ShowItem(show = item)
        }
    }
}