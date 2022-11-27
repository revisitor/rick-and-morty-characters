package ru.mtrefelov.rickandmorty.data.episode

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mtrefelov.network.retrofit

import ru.mtrefelov.rickandmorty.core.episode.Episode
import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository

private typealias EpisodeAction = (List<Episode>) -> Unit

class DefaultEpisodeRepository : EpisodeRepository {
    private val api: EpisodeApi = retrofit.create(EpisodeApi::class.java)

    override fun getByIds(episodeIds: List<Int>, action: EpisodeAction) {
        val call = api.getByIds(episodeIds)
        val callback = EpisodeCallback(action)
        call.enqueue(callback)
    }
}

private class EpisodeCallback(private val action: EpisodeAction) : Callback<List<EpisodeJson>> {
    override fun onResponse(call: Call<List<EpisodeJson>>, response: Response<List<EpisodeJson>>) {
        val episodesResponse = response.body()!!
        action(episodesResponse.toCore())
    }

    override fun onFailure(call: Call<List<EpisodeJson>>, t: Throwable) {
        throw t
    }
}
