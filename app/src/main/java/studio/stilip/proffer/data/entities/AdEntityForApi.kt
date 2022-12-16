package studio.stilip.proffer.data.entities

data class AdEntityForApi(
    val id: Int,
    val photo: String,
    val name: String,
    val price: Int,
    val description: String,
    val characteristics: String,
    val address: String,
    val categories: List<String>,
    val idSeller: Int,
)
