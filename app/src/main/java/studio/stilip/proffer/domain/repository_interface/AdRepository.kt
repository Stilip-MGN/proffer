package studio.stilip.proffer.domain.repository_interface

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Ad

interface AdRepository {

    fun getRecommendedAds(): Single<List<Ad>>

    fun getFavoritesAds(): Single<List<Ad>>
}