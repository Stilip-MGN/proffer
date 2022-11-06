package studio.stilip.proffer.data.dto

import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.domain.entities.Ad

fun AdEntityForApi.toDomain(): Ad =
    Ad(
        id = this.id,
        photo = this.photo,
        name = this.name,
        price = this.price,
        description = this.description,
        characteristics = this.characteristics,
        address = this.address,
        idSeller = this.idSeller
    )