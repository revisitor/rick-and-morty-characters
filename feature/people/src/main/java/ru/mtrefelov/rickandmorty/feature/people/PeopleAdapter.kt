package ru.mtrefelov.rickandmorty.feature.people

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.mtrefelov.rickandmorty.core.person.Person

import ru.mtrefelov.rickandmorty.feature.people.databinding.LoadButtonBinding
import ru.mtrefelov.rickandmorty.feature.people.databinding.PersonBinding

class PeopleAdapter : ListAdapter<PersonViewHolderModel, PersonViewHolder>(ItemCallback()) {
    private var onPersonClicked: ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            PERSON -> PersonBinding.inflate(inflater, parent, false)
            LOAD_BUTTON -> LoadButtonBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Unknown viewType")
        }

        return PersonViewHolder(binding)
    }

    companion object {
        const val PERSON = 1
        const val LOAD_BUTTON = 2
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val data = currentList[position]
        holder.bind(data)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PersonViewHolderModel.PersonItem -> PERSON
            else -> LOAD_BUTTON
        }
    }

    fun setCharacters(characters: Iterable<Person>, onAdded: Runnable) {
        val data = characters.map { PersonViewHolderModel.PersonItem(it, onPersonClicked) }
        submitList(data, onAdded)
    }

    fun showLoadButton(onClick: () -> Unit) {
        val buttonData = PersonViewHolderModel.LoadButton(onClick)
        submitList(currentList + buttonData)
    }

    fun removeLoadButton(onRemoved: () -> Unit) = with(currentList) {
        if (isNotEmpty() && last() is PersonViewHolderModel.LoadButton) {
            submitList(subList(0, lastIndex), onRemoved)
        }
    }

    fun setOnCharacterClicked(callback: (Person) -> Unit) {
        onPersonClicked = callback
    }
}

private class ItemCallback : DiffUtil.ItemCallback<PersonViewHolderModel>() {
    override fun areContentsTheSame(oldItem: PersonViewHolderModel, newItem: PersonViewHolderModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: PersonViewHolderModel, newItem: PersonViewHolderModel): Boolean {
        return oldItem == newItem
    }
}