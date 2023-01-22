package studio.stilip.proffer.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: Int = 1,

    @SerialName("username")
    val login: String = "",

    val name: String = "",

    val city: String = "",

    val phone: String = "",

    @SerialName("email")
    val mail: String = "",

    @SerialName("password")
    val password: String = "",
)
