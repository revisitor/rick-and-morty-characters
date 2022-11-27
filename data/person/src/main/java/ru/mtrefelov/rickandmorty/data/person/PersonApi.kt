package ru.mtrefelov.rickandmorty.data.person

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {
    @GET("api/character")
    fun getCharactersPage(@Query("page") pageNumber: Int): Call<PeoplePageJson>
}