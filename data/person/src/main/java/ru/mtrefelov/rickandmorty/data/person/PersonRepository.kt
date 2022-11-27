package ru.mtrefelov.rickandmorty.data.person

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mtrefelov.network.retrofit

import ru.mtrefelov.rickandmorty.core.person.Person
import ru.mtrefelov.rickandmorty.core.person.PersonPaginatedRepository

private typealias CharactersAction = (List<Person>) -> Unit

class PersonRepository : PersonPaginatedRepository {
    private val api: PersonApi = retrofit.create(PersonApi::class.java)

    private var _lastPageNumber: Int? = null

    override fun getPage(page: Int, action: CharactersAction) {
        val call = api.getCharactersPage(page)
        val callback = CharacterPageCallback(action)
        call.enqueue(callback)
    }

    private inner class CharacterPageCallback(private val action: CharactersAction) : Callback<PeoplePageJson> {
        override fun onResponse(call: Call<PeoplePageJson>, response: Response<PeoplePageJson>) {
            when (response.code()) {
                200 -> handleOk(response.body()!!)
                else -> action(emptyList())
            }
        }

        private fun handleOk(page: PeoplePageJson) {
            if (_lastPageNumber == null) {
                _lastPageNumber = page.info.lastPageNumber
            }

            action(page.people.toCore())
        }

        override fun onFailure(call: Call<PeoplePageJson>, t: Throwable) {
            throw t
        }
    }

    override fun getLastPageNumber(): Int? {
        return _lastPageNumber
    }
}