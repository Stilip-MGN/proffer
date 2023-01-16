package studio.stilip.proffer.domain.entities

data class Ad(
    val id: Int,
    val photos: List<String>,
    val name: String,
    val price: Int,
    val phone: String,
    val description: String,
    val characteristics: String,
    val isSell: Boolean,
    val address: String,
    val idSeller: Int,
    val categories: List<String>,
    var isFavorite: Boolean = false,
)
