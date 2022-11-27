package ru.mtrefelov.rickandmorty.core.person

enum class Gender(private val value: String) {
    MALE("male"),
    FEMALE("female"),
    GENDERLESS("genderless"),
    UNKNOWN("unknown");

    override fun toString() = value

    companion object {
        fun fromString(gender: CharSequence) = when (gender.toString().lowercase()) {
            "male" -> MALE
            "female" -> FEMALE
            "genderless" -> GENDERLESS
            else -> UNKNOWN
        }
    }
}