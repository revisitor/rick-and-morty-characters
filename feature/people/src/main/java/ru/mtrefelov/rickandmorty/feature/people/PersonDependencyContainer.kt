package ru.mtrefelov.rickandmorty.feature.people

import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository

interface PersonDependencyContainer {
    fun getPersonRepository(): PersonPaginatedRepository
}