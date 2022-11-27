package ru.mtrefelov.rickandmorty.core.person

data class Person(
    val id: Int,
    val name: String,
    val gender: Gender,
    val imageUrl: String,
    val episodeIds: List<Int>,
)