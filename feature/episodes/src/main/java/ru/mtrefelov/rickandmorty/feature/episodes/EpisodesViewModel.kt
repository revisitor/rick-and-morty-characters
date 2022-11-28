package ru.mtrefelov.rickandmorty.feature.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import ru.mtrefelov.rickandmorty.core.episode.Episode
import ru.mtrefelov.rickandmorty.core.episode.EpisodeRepository

class EpisodesViewModel(private val repository: EpisodeRepository) : ViewModel() {
    class Factory(private val repository: EpisodeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return EpisodesViewModel(repository) as T
        }
    }

    private val _episodes = MutableLiveData<List<Episode>>(emptyList())
    val episodes: LiveData<List<Episode>>
        get() = _episodes

    suspend fun getEpisodes(ids: List<Int>) {
        try {
            val episodes = repository.getByIds(ids)
            _episodes.postValue(episodes)
        } catch (_: Exception) {
            _episodes.postValue(emptyList())
        }
    }
}
