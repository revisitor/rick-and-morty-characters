package ru.mtrefelov.rickandmorty.feature.people

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

import ru.mtrefelov.rickandmorty.feature.people.databinding.LoadButtonBinding
import ru.mtrefelov.rickandmorty.feature.people.databinding.PersonBinding
import ru.mtrefelov.rickandmorty.imageloader.load

class PersonViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PersonViewHolderModel) {
        when (data) {
            is PersonViewHolderModel.PersonItem -> bindPersonItem(data)
            is PersonViewHolderModel.LoadButton -> bindLoadButton(data)
        }
    }

    private fun bindPersonItem(data: PersonViewHolderModel.PersonItem) {
        with(binding as PersonBinding) {
            character.setOnClickListener {
                data.onClick?.invoke(data.person)
            }

            data.person.let {
                name.text = it.name
                gender.text = root.resources.getString(R.string.gender_placeholder, it.gender)
                characterImage.load(it.imageUrl)
            }
        }
    }

    private fun bindLoadButton(data: PersonViewHolderModel.LoadButton) {
        (binding as LoadButtonBinding)
            .loadButton
            .setOnClickListener {
                data.onClickListener()
            }
    }
}
