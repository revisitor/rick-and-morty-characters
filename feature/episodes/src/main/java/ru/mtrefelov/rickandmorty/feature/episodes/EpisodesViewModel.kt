package ru.mtrefelov.rickandmorty.feature.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mtrefelov.rickandmorty.core.episode.Episode
import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository
import ru.mtrefelov.rickandmorty.data.episode.DefaultEpisodeRepository

class EpisodesViewModel : ViewModel() {
    private val _episodes = MutableLiveData<List<Episode>>(emptyList())
    val episodes: LiveData<List<Episode>>
        get() = _episodes

    private val repository: EpisodeRepository = DefaultEpisodeRepository()

    fun getEpisodes(ids: List<Int>) {
        repository.getByIds(ids) {
            _episodes.postValue(it)
        }
    }

    fun clearEpisodes() {
        _episodes.value = emptyList()
    }
}
