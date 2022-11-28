package ru.mtrefelov.rickandmorty.app

import android.app.Application

import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository

import ru.mtrefelov.rickandmorty.data.episode.DefaultEpisodeRepository
import ru.mtrefelov.rickandmorty.data.person.PersonRepository

import ru.mtrefelov.rickandmorty.feature.episodes.EpisodeDependencyContainer
import ru.mtrefelov.rickandmorty.feature.people.PersonDependencyContainer

class RickApplication : Application(), PersonDependencyContainer, EpisodeDependencyContainer {
    private lateinit var personRepository: PersonPaginatedRepository
    private lateinit var episodeRepository: EpisodeRepository

    override fun onCreate() {
        super.onCreate()
        personRepository = PersonRepository()
        episodeRepository = DefaultEpisodeRepository()
    }

    override fun getEpisodeRepository(): EpisodeRepository {
        return episodeRepository
    }

    override fun getPersonRepository(): PersonPaginatedRepository {
        return personRepository
    }
}