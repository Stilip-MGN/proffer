package studio.stilip.proffer.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AdForApi(
    @SerialName("id")
    val id: Int = 1,

    @SerialName("image")
    val photo: String = "",

    @SerialName("title")
    val name: String = "",

    @SerialName("price")
    val price: Double = 1.0,

    @SerialName("number")
    val phone: String = "",

    @SerialName("overview")
    val description: String = "",

    @SerialName("owner")
    val idOwner: Int = 1,

    @SerialName("selling")
    val isSell: Boolean = true,

    @SerialName("category")
    val category: String = "",

    var isFavorite: Boolean = false,
)
