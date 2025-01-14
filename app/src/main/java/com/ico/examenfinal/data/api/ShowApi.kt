package com.ico.myapplication.data.api

import com.ico.examenfinal.data.api.model.Cast
import com.ico.examenfinal.data.api.model.ShowSearch
import com.ico.myapplication.data.api.model.Show
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowApi {
    @GET(ApiConstants.END_POINT)
    suspend fun getShows() : List<Show>

    @GET("${ApiConstants.END_POINT}/{id}?embed=cast")
    suspend fun getCast(@Path("id") id: String) : Cast

    @GET("search/shows")
    suspend fun getShowsSearch(@Query("q") query: String) : List<ShowSearch>


    companion object {
        var apiService: ShowApi? = null
        fun getInstance() : ShowApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ShowApi::class.java)
            }
            return apiService!!
        }
    }
}