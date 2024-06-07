package com.ico.examenfinal.di

import com.ico.myapplication.data.api.ApiConstants
import com.ico.myapplication.data.api.ShowApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShowApiModule {

    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): ShowApi {
        return builder
            .build()
            .create(ShowApi::class.java)

    }

    /**
     * Create retrofit object
     */
    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}
