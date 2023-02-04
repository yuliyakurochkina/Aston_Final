package com.example.aston_final.model

data class Episode(
    val id: Int?,
    val name: String?,
    val air_date: String?,
    val episode: String?,
    val characters: Array<String>?,
    val url: String?,
    val created: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Episode

        if (id != other.id) return false
        if (name != other.name) return false
        if (air_date != other.air_date) return false
        if (episode != other.episode) return false
        if (characters != null) {
            if (other.characters == null) return false
            if (!characters.contentEquals(other.characters)) return false
        } else if (other.characters != null) return false
        if (url != other.url) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (air_date?.hashCode() ?: 0)
        result = 31 * result + (episode?.hashCode() ?: 0)
        result = 31 * result + (characters?.contentHashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        return result
    }
}

data class AllEpisodes(
    val info: Info,
    val results: MutableList<Episode>
)