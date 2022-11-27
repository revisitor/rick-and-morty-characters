package ru.mtrefelov.rickandmorty.feature.people

import androidx.lifecycle.*
import ru.mtrefelov.rickandmorty.core.person.Person
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository
import ru.mtrefelov.rickandmorty.data.person.PersonRepository

enum class State {
    CLEAR,
    PAGE_RECEIVED,
    LOADING_PAGE,
    LAST_PAGE_RECEIVED
}

class PeopleViewModel : ViewModel() {
    private val _characters = MutableLiveData<List<Person>>(emptyList())
    val characters: LiveData<List<Person>>
        get() = _characters

    private var _state = State.CLEAR
    val state get() = _state

    private var nextPage = 1
    private val lastPage get() = repository.getLastPageNumber()

    private val repository: PersonPaginatedRepository = PersonRepository()

    fun getNextPage() {
        _state = State.LOADING_PAGE
        repository.getPage(nextPage) {
            _state = if (nextPage < lastPage!!) {
                nextPage += 1
                State.PAGE_RECEIVED
            } else {
                State.LAST_PAGE_RECEIVED
            }

            _characters.postValue(_characters.value!!.plus(it))
        }
    }
}
