package com.ico.myapplication.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ico.myapplication.data.api.ShowApi
import com.ico.myapplication.data.api.model.Show
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var showListResponse:List<Show> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getShowList() {
        viewModelScope.launch {
            val apiService = ShowApi.getInstance()
            try {
                val showList = apiService.getShows()
                showListResponse = showList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}