package studio.stilip.proffer.data.dto

import studio.stilip.proffer.data.entities.ProfileForApi
import studio.stilip.proffer.domain.entities.Profile

fun ProfileForApi.toDomain(): Profile =
    Profile(
        id = this.user.id,
        name = this.user.name,
        photo = this.photo,
        mail = this.user.mail,
        rating = 4.4,
        countReviews = 25
    )

