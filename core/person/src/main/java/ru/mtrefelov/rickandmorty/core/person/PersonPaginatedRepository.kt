package ru.mtrefelov.rickandmorty.core.person

interface PersonPaginatedRepository {
    fun getPage(page: Int, action: (List<Person>) -> Unit)
    fun getLastPageNumber(): Int?
}