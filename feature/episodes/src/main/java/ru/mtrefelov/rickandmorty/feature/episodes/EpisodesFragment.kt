package ru.mtrefelov.rickandmorty.feature.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import ru.mtrefelov.rickandmorty.feature.episodes.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment() {
    private lateinit var viewModel: EpisodesViewModel

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    private fun createViewModel(): EpisodesViewModel {
        val container = requireActivity().application as EpisodeDependencyContainer
        val factory = EpisodesViewModel.Factory(container.getEpisodeRepository())
        return ViewModelProvider(this, factory)[EpisodesViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(binding.episodesToolbar)

        val episodesAdapter = EpisodesAdapter()
        binding.episodes.apply {
            adapter = episodesAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.episodes.observe(viewLifecycleOwner) {
            episodesAdapter.setEpisodes(it)
        }


        with(activity as AppCompatActivity) {
            val args = intent!!
            supportActionBar!!.apply {
                val name = args.getStringExtra("PERSON_NAME")
                title = resources.getString(R.string.episodes_toolbar, name)
            }

            val episodesIds = args.getIntArrayExtra("PERSON_EPISODES_IDS")!!.toList()
            GlobalScope.launch {
                viewModel.getEpisodes(episodesIds)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}