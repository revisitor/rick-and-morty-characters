package ru.mtrefelov.rickandmorty.data.person

import ru.mtrefelov.rickandmorty.core.person.Gender
import ru.mtrefelov.rickandmorty.core.person.Person

internal fun List<PeoplePageJson.PersonJson>.toCore() = map {
    it.toCore()
}

internal fun PeoplePageJson.PersonJson.toCore() = Person(
    id = id,
    name = name,
    gender = Gender.fromString(gender),
    imageUrl = imageUrl,
    episodeIds = episodeUrls.map(::episodeLinkToId)
)

private fun episodeLinkToId(url: CharSequence): Int {
    return url.split("/").last().toInt()
}