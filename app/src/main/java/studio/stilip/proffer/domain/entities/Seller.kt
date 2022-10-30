package studio.stilip.proffer.domain.entities

data class Seller(
    val id: Int,
    val name: String,
    val photo: String,
    val rating: Double,
    val countReviews: Int
)
