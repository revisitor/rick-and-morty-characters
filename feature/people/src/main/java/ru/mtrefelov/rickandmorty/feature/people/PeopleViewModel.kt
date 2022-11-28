package ru.mtrefelov.rickandmorty.feature.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import ru.mtrefelov.rickandmorty.core.person.Person
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository

class PeopleViewModel(private val repository: PersonPaginatedRepository) : ViewModel() {
    class Factory(private val repository: PersonPaginatedRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return PeopleViewModel(repository) as T
        }
    }

    private val _characters = MutableLiveData<List<Person>>(emptyList())
    val characters: LiveData<List<Person>>
        get() = _characters

    private var nextPage = 1
    private val lastPage get() = repository.getLastPageNumber()

    suspend fun getNextPage() {
        if (isClear() || hasMorePages()) {
            try {
                val people = repository.getPage(nextPage)
                nextPage++
                _characters.postValue(_characters.value!!.plus(people))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isClear(): Boolean {
        return characters.value!!.isEmpty()
    }

    fun hasMorePages(): Boolean {
        return lastPage?.let { it > nextPage } ?: false
    }
}
