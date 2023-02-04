package com.example.aston_final.model

data class Character(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val origin: LocationForCharacter?,
    val location: LocationForCharacter?,
    val image: String?,
    val episode: Array<String>?,
    val url: String?,
    val created: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Character

        if (id != other.id) return false
        if (name != other.name) return false
        if (status != other.status) return false
        if (species != other.species) return false
        if (type != other.type) return false
        if (gender != other.gender) return false
        if (origin != other.origin) return false
        if (location != other.location) return false
        if (image != other.image) return false
        if (episode != null) {
            if (other.episode == null) return false
            if (!episode.contentEquals(other.episode)) return false
        } else if (other.episode != null) return false
        if (url != other.url) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        result = 31 * result + (species?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (origin?.hashCode() ?: 0)
        result = 31 * result + (location?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (episode?.contentHashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        return result
    }
}

data class LocationForCharacter(
    val name: String?,
    val url: String?
)

data class AllCharacters(
    val info: Info,
    val results: MutableList<Character>
)