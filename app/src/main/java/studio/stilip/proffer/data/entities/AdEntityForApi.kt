package studio.stilip.proffer.data.entities

data class AdEntityForApi(
    val id: Int,
    val photos: List<String>,
    val name: String,
    val price: Int,
    val phone: String,
    val description: String,
    val characteristics: String,
    val isSell: Boolean,
    val address: String,
    val categories: List<String>,
    val idSeller: Int,
)
