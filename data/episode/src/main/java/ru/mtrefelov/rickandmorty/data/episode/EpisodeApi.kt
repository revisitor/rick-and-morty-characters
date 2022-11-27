package ru.mtrefelov.rickandmorty.data.episode

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("api/episode/{ids}")
    fun getByIds(@Path("ids") episodeIds: Iterable<Int>): Call<List<EpisodeJson>>
}