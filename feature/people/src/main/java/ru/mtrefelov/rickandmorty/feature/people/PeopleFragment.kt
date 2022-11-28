package ru.mtrefelov.rickandmorty.feature.people

import android.content.Intent
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

import ru.mtrefelov.rickandmorty.feature.people.databinding.FragmentPeopleBinding

class PeopleFragment : Fragment() {
    private lateinit var viewModel: PeopleViewModel

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

//    private var onPersonClicked: ((String, List<Int>) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    private fun createViewModel(): PeopleViewModel {
        val container = requireActivity().application as PersonDependencyContainer
        val factory = PeopleViewModel.Factory(container.getPersonRepository())
        return ViewModelProvider(this, factory)[PeopleViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity)
            .setSupportActionBar(binding.charactersToolbar)

        val peopleAdapter = PeopleAdapter().apply {
            setOnCharacterClicked {
                val intent = Intent("ru.mtrefelov.rickandmorty.episodes.open")
                    .putExtra("PERSON_NAME", it.name)
                    .putExtra("PERSON_EPISODES_IDS", it.episodeIds.toIntArray())

                requireActivity().startActivity(intent)
            }
        }

        binding.characters.apply {
            adapter = peopleAdapter
            layoutManager = LinearLayoutManager(view.context)
        }

        viewModel.characters.observe(viewLifecycleOwner) {
            with(peopleAdapter) {
                setCharacters(it) {
                    if (viewModel.hasMorePages()) {
                        showLoadButton {
                            removeLoadButton {
                                GlobalScope.launch {
                                    viewModel.getNextPage()
                                }
                            }
                        }
                    }
                }
            }
        }

        if (viewModel.isClear()) {
            GlobalScope.launch {
                viewModel.getNextPage()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
//
//    fun setOnPersonClicked(onPersonClicked: (String, List<Int>) -> Unit) {
//        this.onPersonClicked = onPersonClicked
//    }
}