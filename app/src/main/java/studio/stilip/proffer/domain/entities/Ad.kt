package studio.stilip.proffer.domain.entities

data class Ad(
    val id: Int = 1,
    val photos: List<String> = emptyList(),
    val name: String = "",
    val price: Double = 1.0,
    val phone: String= "",
    val description: String= "",
    val characteristics: String= "",
    val isSell: Boolean = true,
    val address: String= "",
    val idSeller: Int = 1,
    val categories: List<String> = emptyList(),
    var isFavorite: Boolean = false,
)
