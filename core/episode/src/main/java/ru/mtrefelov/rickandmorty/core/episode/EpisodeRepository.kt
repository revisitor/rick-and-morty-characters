package ru.mtrefelov.rickandmorty.core.episode

interface EpisodeRepository {
    suspend fun getByIds(episodeIds: List<Int>): List<Episode>
}