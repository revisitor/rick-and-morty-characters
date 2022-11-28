package ru.mtrefelov.rickandmorty.feature.episodes

import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository

interface EpisodeDependencyContainer {
    fun getEpisodeRepository(): EpisodeRepository
}