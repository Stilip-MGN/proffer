package studio.stilip.proffer.domain.repository_interface

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Ad

interface AdRepository {

    fun getRecommendedAds(): Single<List<Ad>>

    fun getFavoritesAds(): Single<List<Ad>>

    fun getAdById(id: Int): Single<Ad>

    fun addAdToFavoriteById(id: Int): Completable

    fun removeAdFromFavoriteById(id: Int): Completable

    fun getSimilarAds(id: Int): Single<List<Ad>>

    fun addAd(ad: Ad): Completable

    fun getAdsByUserId(id: Int): Single<List<Ad>>

    fun getAdsContainsString(string: String): Single<List<Ad>>

    fun getComplitedAdsByUserId(id: Int): Single<List<Ad>>
}