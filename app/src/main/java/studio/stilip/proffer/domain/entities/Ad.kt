package studio.stilip.proffer.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ad(
    @SerialName("id")
    val id: Int = 1,

    val photos: List<String> = emptyList(),

    @SerialName("title")
    val name: String = "",

    @SerialName("price")
    val price: Double = 1.0,

    @SerialName("number")
    val phone: String= "",

    @SerialName("overview")
    val description: String= "",

    val characteristics: String= "",

    @SerialName("selling")
    val isSell: Boolean = true,

    val address: String= "",

    @SerialName("owner")
    val idSeller: Int = 1,

    val categories: List<String> = emptyList(),

    var isFavorite: Boolean = false,
)
