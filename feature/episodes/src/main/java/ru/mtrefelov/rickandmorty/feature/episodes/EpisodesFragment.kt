package ru.mtrefelov.rickandmorty.feature.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import ru.mtrefelov.rickandmorty.feature.episodes.databinding.FragmentEpisodesBinding

class EpisodesFragment : Fragment() {
    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EpisodesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.episodesToolbar)
            onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        viewModel.clearEpisodes()
                        remove()
                        parentFragmentManager.popBackStack()
                    }
                })
        }

        val episodesAdapter = EpisodesAdapter()
        binding.episodes.apply {
            adapter = episodesAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.episodes.observe(viewLifecycleOwner) {
            episodesAdapter.setEpisodes(it)
        }

        with(activity as AppCompatActivity) {
            supportActionBar!!.apply {
                val name = requireArguments().getString(ARGUMENT_NAME)
                title = resources.getString(R.string.episodes_toolbar, name)
            }
        }

        val episodesIds = requireArguments().getIntArray(ARGUMENT_EPISODES_IDS)!!.toList()
        GlobalScope.launch {
            viewModel.getEpisodes(episodesIds)
        }
    }

    companion object {
        const val ARGUMENT_EPISODES_IDS = "PERSON_EPISODES_IDS"
        const val ARGUMENT_NAME = "PERSON_NAME"
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}