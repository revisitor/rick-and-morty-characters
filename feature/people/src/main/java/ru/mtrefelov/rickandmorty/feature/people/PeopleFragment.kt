package ru.mtrefelov.rickandmorty.feature.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import ru.mtrefelov.rickandmorty.feature.people.databinding.FragmentPeopleBinding

class PeopleFragment(private val onPersonClicked: (String, List<Int>) -> Unit) : Fragment() {
    private val viewModel: PeopleViewModel by viewModels()

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity)
            .setSupportActionBar(binding.charactersToolbar)

        val peopleAdapter = PeopleAdapter().apply {
            setOnCharacterClicked {
                onPersonClicked(it.name, it.episodeIds)
            }
        }

        binding.characters.apply {
            adapter = peopleAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            with(peopleAdapter) {
                setCharacters(it) {
                    if (viewModel.state == State.PAGE_RECEIVED) {
                        showLoadButton {
                            removeLoadButton {
                                viewModel.getNextPage()
                            }
                        }
                    }
                }
            }
        }

        if (viewModel.state == State.CLEAR) {
            viewModel.getNextPage()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}