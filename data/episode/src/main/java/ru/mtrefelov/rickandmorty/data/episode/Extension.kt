package ru.mtrefelov.rickandmorty.data.episode

import ru.mtrefelov.rickandmorty.core.episode.Episode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

internal fun List<EpisodeJson>.toCore() = map {
    it.toCore()
}

internal fun EpisodeJson.toCore() = Episode(
    id = id,
    title = name,
    airDate = LocalDate.parse(this.airDate, airDateFormatter),
)

val airDateFormatter: DateTimeFormatter = DateTimeFormatter
    .ofPattern("MMMM d, yyyy", Locale.ENGLISH)

