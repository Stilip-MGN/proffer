package studio.stilip.proffer.domain.entities

data class User(
    val id: Int,
    val login: String,
    val name: String,
    val city: String,
    val phone: String,
    val mail: String,
    val password: String,
)
