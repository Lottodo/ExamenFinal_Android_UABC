package com.ico.myapplication.data.api.model

data class Show(
    val id: Int,
    val url: String,
    val name: String,
    val genres: List<String>,
    val premiered: String,
    val network: Network,
    val language: String,
    val summary: String,
    val image: Image
)

data class Network(
    val country: Country
)

data class Country(
    val name: String,
    val code: String
)

data class Image(
    val medium: String,
    val original: String
)