package studio.stilip.proffer.domain.entities

data class Ad(
    val id: Int,
    val photo: String,
    val name: String,
    val price: Int,
    val description: String,
    val characteristics: String,
    val address: String,
    val id_seller: Int
)
