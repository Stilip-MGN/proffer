package studio.stilip.proffer.domain.repository_interface

import io.reactivex.Completable
import io.reactivex.Single
import studio.stilip.proffer.domain.entities.Ad

interface AdRepository {

    fun getRecommendedAds(userId: Int): Single<List<Ad>>

    fun getFavoritesAds(): Single<List<Ad>>

    fun getAdById(idAd: Int, idUser: Int): Single<Ad>

    fun addAdToFavoriteById(id: Int): Completable

    fun removeAdFromFavoriteById(id: Int): Completable

    fun getSimilarAds(id: Int): Single<List<Ad>>

    fun addAd(ad: Ad): Completable

    fun getAdsByUserId(id: Int): Single<List<Ad>>

    fun getAdsContainsString(string: String, userId: Int): Single<List<Ad>>

    fun getComplitedAdsByUserId(id: Int): Single<List<Ad>>

    fun saveEditAd(ad: Ad): Completable

    fun deleteAdById(id: Int): Completable
}