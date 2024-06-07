package com.ico.examenfinal.data.api.model

data class Cast(
    val _embedded: Embedded
)

data class Embedded(
    val cast: List<CastShow>
)

data class CastShow(
    val person: Person,
    val character: CharacterShow
)

data class Person(
    val id: Int,
    val name: String,
    val image: ImageShow
)

data class ImageShow(
    val medium: String
)

data class CharacterShow(
    val id: Int,
    val name: String
)