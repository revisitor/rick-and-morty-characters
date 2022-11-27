package ru.mtrefelov.rickandmorty.data.episode

import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApi {
    @GET("api/episode/{ids}")
    suspend fun getByIds(@Path("ids") episodeIds: Iterable<Int>): List<EpisodeJson>
}