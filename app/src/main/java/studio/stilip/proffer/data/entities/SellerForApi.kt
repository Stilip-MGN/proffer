package studio.stilip.proffer.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileForApi(
    @SerialName("user")
    val user: UserForProfileForApi,

    @SerialName("image")
    val photo: String
)

@Serializable
data class UserForProfileForApi(
    @SerialName("id")
    val id: Int,

    @SerialName("full_name")
    val name: String,

    @SerialName("email")
    val mail: String,
)