package studio.stilip.proffer.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFromApi(
    @SerialName("id")
    val id: Int = -1,

    @SerialName("username")
    val login: String = "",

    @SerialName("email")
    val mail: String = "",

    @SerialName("password")
    val password: String = "",
)
