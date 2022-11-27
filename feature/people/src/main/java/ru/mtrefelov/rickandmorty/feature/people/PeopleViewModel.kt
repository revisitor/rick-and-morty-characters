package ru.mtrefelov.rickandmorty.feature.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import ru.mtrefelov.rickandmorty.core.person.Person
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository
import ru.mtrefelov.rickandmorty.data.person.PersonRepository

class PeopleViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Person>>(emptyList())
    val characters: LiveData<List<Person>>
        get() = _characters

    private var nextPage = 1
    private val lastPage get() = repository.getLastPageNumber()

    private val repository: PersonPaginatedRepository = PersonRepository()

    suspend fun getNextPage() {
        if (isClear() || hasMorePages()) {
            val people = repository.getPage(nextPage)
            nextPage++
            _characters.postValue(_characters.value!!.plus(people))
        }
    }

    fun isClear(): Boolean {
        return characters.value!!.isEmpty()
    }

    fun hasMorePages(): Boolean {
        return lastPage?.let { it > nextPage } ?: false
    }
}
