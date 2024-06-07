package com.ico.myapplication.data.repository

import com.ico.myapplication.data.api.ShowApi
import com.ico.myapplication.data.api.model.Show
import javax.inject.Inject

class ShowRepository @Inject constructor(
    private val showApi: ShowApi
){

    suspend fun getShows():List<Show>{
        return showApi.getShows()
    }
}