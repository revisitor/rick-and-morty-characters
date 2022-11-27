package ru.mtrefelov.rickandmorty.data.person

import com.squareup.moshi.Json

class PeoplePageJson(
    val info: InfoJson,

    @Json(name = "results")
    val people: List<PersonJson>,
) {
    class InfoJson(
        @Json(name = "pages")
        val lastPageNumber: Int,
    )

    class PersonJson(
        val id: Int,
        val name: String,
        val gender: String,

        @Json(name = "image")
        val imageUrl: String,

        @Json(name = "episode")
        val episodeUrls: List<String>,
    )
}