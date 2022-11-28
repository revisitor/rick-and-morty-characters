package ru.mtrefelov.rickandmorty.feature.episodes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mtrefelov.rickandmorty.feature.episodes.databinding.ActivityEpisodesBinding

class EpisodesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}