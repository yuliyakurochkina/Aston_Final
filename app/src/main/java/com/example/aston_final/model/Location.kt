package com.example.aston_final.model

data class Location(
    val id: Int?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val residents: Array<String>?,
    val url: String?,
    val created: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Location

        if (id != other.id) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (dimension != other.dimension) return false
        if (residents != null) {
            if (other.residents == null) return false
            if (!residents.contentEquals(other.residents)) return false
        } else if (other.residents != null) return false
        if (url != other.url) return false
        if (created != other.created) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (type?.hashCode() ?: 0)
        result = 31 * result + (dimension?.hashCode() ?: 0)
        result = 31 * result + (residents?.contentHashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (created?.hashCode() ?: 0)
        return result
    }
}

data class AllLocations(
    val info: Info,
    val results: MutableList<Location>
)