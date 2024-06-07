package com.ico.myapplication.data.api.model

import java.io.Serializable

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val rating: Rating,
    val genres: List<String>,
    val premiered: String,
    val network: Network,
    val language: String,
    val summary: String,
    val image: Image,
) : Serializable

data class Network(
    val country: Country
) : Serializable

data class Country(
    val name: String,
    val code: String
) : Serializable

data class Image(
    val medium: String,
    val original: String
) : Serializable

data class Rating(
    val average: Double?
) : Serializable