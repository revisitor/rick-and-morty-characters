package ru.mtrefelov.rickandmorty.core.episode

import java.time.LocalDate

data class Episode(
    val id: Int,
    val title: String,
    val airDate: LocalDate,
)
