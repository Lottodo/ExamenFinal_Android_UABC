package com.ico.myapplication.data.api

import com.ico.myapplication.data.api.model.Show
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ShowApi {
    @GET(ApiConstants.END_POINTS)
    suspend fun getShows() : List<Show>

    companion object {
        var apiService: ShowApi? = null
        fun getInstance() : ShowApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.tvmaze.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ShowApi::class.java)
            }
            return apiService!!
        }
    }
}