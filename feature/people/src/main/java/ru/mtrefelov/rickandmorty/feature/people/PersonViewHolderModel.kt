package ru.mtrefelov.rickandmorty.feature.people

import ru.mtrefelov.rickandmorty.core.person.Person

sealed class PersonViewHolderModel {
    data class PersonItem(
        val person: Person,
        var onClick: ((Person) -> Unit)?,
    ) : PersonViewHolderModel()

    data class LoadButton(val onClickListener: () -> Unit) : PersonViewHolderModel()
}
