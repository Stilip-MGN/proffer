package studio.stilip.proffer.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApiForLogin(

    @SerialName("username")
    val login: String,

    @SerialName("password")
    val password: String
)
