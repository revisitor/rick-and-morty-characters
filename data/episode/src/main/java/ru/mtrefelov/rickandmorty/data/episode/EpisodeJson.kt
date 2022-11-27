package ru.mtrefelov.rickandmorty.data.episode

import com.squareup.moshi.Json

class EpisodeJson(
    val id: Int,
    val name: String,

    @Json(name = "air_date")
    val airDate: String,
)
