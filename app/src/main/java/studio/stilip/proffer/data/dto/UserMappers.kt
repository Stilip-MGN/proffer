package studio.stilip.proffer.data.dto

import studio.stilip.proffer.data.entities.UserForDB
import studio.stilip.proffer.data.entities.UserFromApi
import studio.stilip.proffer.domain.entities.User

fun User.toDB(): UserForDB =
    UserForDB(
        id = this.id,
        name = this.name,
        login = this.login,
        city = this.city,
        phone = this.phone,
        mail = this.mail
    )

fun UserForDB.toDomain(): User =
    User(
        id = this.id,
        login = this.login,
        name = this.name,
        city = this.city,
        mail = this.mail,
        password = ""
    )

fun UserFromApi.toDomain(): User =
    User(
        id = this.id,
        login = this.login,
        name = "",
        city = "",
        mail = this.mail,
        password = ""
    )