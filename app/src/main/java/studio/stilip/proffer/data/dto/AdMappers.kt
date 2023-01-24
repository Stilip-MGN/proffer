package studio.stilip.proffer.data.dto

import studio.stilip.proffer.data.entities.AdEntityForApi
import studio.stilip.proffer.data.entities.AdForApi
import studio.stilip.proffer.domain.entities.Ad

fun AdEntityForApi.toDomain(): Ad =
    Ad(
        id = this.id,
        photos = this.photos,
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
        photos = this.photos,
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

fun AdForApi.toDomain(): Ad {

    val photosList = mutableListOf(this.photo)
    this.photos.forEach { ph -> photosList.add(ph.photo) }

    return Ad(
        id = this.id,
        photos = photosList,
        name = this.name,
        price = this.price,
        phone = this.phone,
        description = this.description,
        characteristics = "",
        isSell = this.isSell,
        categories = listOf(this.category),
        idSeller = this.idOwner
    )
}
