package studio.stilip.proffer.domain.entities

data class Profile(
    val id: Int,
    val name: String,
    val photo: String,
    val mail: String,
    val rating: Double,
    val countReviews: Int
)
