package studio.stilip.proffer.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class AdEntityForApi(
    val id: Int,
    val photos: List<String>,
    val name: String,
    val price: Double,
    val phone: String,
    val description: String,
    val characteristics: String,
    val isSell: Boolean,
    val address: String,
    val categories: List<String>,
    val idSeller: Int,
)
