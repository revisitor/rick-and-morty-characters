package ru.mtrefelov.rickandmorty.core.person

interface PersonPaginatedRepository {
    suspend fun getPage(pageNumber: Int): List<Person>
    fun getLastPageNumber(): Int?
}