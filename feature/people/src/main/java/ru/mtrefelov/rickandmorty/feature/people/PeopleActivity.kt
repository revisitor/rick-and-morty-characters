package ru.mtrefelov.rickandmorty.feature.people

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mtrefelov.rickandmorty.feature.people.databinding.ActivityPeopleBinding

class PeopleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}