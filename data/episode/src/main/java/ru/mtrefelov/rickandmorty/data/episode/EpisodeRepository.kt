package ru.mtrefelov.rickandmorty.data.episode

import ru.mtrefelov.network.retrofit
import ru.mtrefelov.rickandmorty.core.episode.Episode
import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository

class DefaultEpisodeRepository : EpisodeRepository {
    private val api: EpisodeApi = retrofit.create(EpisodeApi::class.java)

    override suspend fun getByIds(episodeIds: List<Int>): List<Episode> {
        return api.getByIds(episodeIds).toCore()
    }
}
