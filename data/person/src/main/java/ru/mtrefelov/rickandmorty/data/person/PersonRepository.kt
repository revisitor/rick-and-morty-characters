package ru.mtrefelov.rickandmorty.data.person

import ru.mtrefelov.network.retrofit
import ru.mtrefelov.rickandmorty.core.person.Person
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository

class PersonRepository : PersonPaginatedRepository {
    private val api: PersonApi = retrofit.create(PersonApi::class.java)

    private var lastPageNumber: Int? = null

    override suspend fun getPage(pageNumber: Int): List<Person> {
        api.getCharactersPage(pageNumber).run {
            if (lastPageNumber == null) {
                lastPageNumber = info.lastPageNumber
            }

            return people.toCore()
        }
    }

    override fun getLastPageNumber(): Int? {
        return lastPageNumber
    }
}