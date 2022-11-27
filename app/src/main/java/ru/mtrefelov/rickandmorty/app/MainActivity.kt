package ru.mtrefelov.rickandmorty.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.mtrefelov.rickandmorty.feature.episodes.EpisodesFragment

import ru.mtrefelov.rickandmorty.feature.people.PeopleFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val peopleFragment = PeopleFragment(::showEpisodeFragment)

    private val episodeFragment: EpisodesFragment by lazy {
        EpisodesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_view, peopleFragment)
        }
    }

    private fun showEpisodeFragment(name: String, ids: List<Int>) {
        episodeFragment.arguments = Bundle().apply {
            putString(EpisodesFragment.ARGUMENT_NAME, name)
            putIntArray(EpisodesFragment.ARGUMENT_EPISODES_IDS, ids.toIntArray())
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_view, episodeFragment)
            addToBackStack(null)
        }
    }
}