package ru.mtrefelov.rickandmorty.core.episode

interface EpisodeRepository {
    fun getByIds(episodeIds: List<Int>, action: (List<Episode>) -> Unit)
}