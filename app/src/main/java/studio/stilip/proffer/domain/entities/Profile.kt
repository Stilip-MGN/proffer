package studio.stilip.proffer.domain.entities

data class Profile(
    val name: String,
    val place: String,
    val countOrders: Int,
    val countAds: Int,
    val countReviews: Int
)
