package studio.stilip.proffer.data.dto

import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.domain.entities.Ad

fun AdEntityForApi.toDomain(): Ad =
    Ad(
        id = this.id,
        photo = this.photo,
        name = this.name,
        price = this.price,
        phone = this.phone,
        description = this.description,
        characteristics = this.characteristics,
        isSell = this.isSell,
        address = this.address,
        categories = this.categories,
        idSeller = this.idSeller
    )

fun Ad.toData(): AdEntityForApi =
    AdEntityForApi(
        id = this.id,
        photo = this.photo,
        name = this.name,
        price = this.price,
        phone = this.phone,
        description = this.description,
        characteristics = this.characteristics,
        isSell = this.isSell,
        address = this.address,
        categories = this.categories,
        idSeller = this.idSeller
    )