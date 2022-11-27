package ru.mtrefelov.rickandmorty.feature.episodes

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mtrefelov.rickandmorty.core.episode.Episode
import ru.mtrefelov.rickandmorty.data.episode.airDateFormatter
import ru.mtrefelov.rickandmorty.feature.episodes.databinding.EpisodeBinding

class EpisodeViewHolder(binding: EpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
    private val title: TextView = binding.title
    private val airDate: TextView = binding.airDate

    fun bind(episode: Episode) {
        title.text = episode.title
        airDate.text = airDate.resources.getString(
            R.string.air_date_placeholder,
            episode.airDate.format(airDateFormatter)
        )
    }
}
