package studio.stilip.proffer.data.repositories

import io.reactivex.rxjava3.core.Single
import studio.stilip.proffer.domain.entities.Ad
import studio.stilip.proffer.domain.repository_interface.AdRepository
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor() : AdRepository {

    //хардкод
    val ads = listOf(
        Ad(1, "", 100, "Кружка"),
        Ad(2, "", 50, "Сок"),
        Ad(3, "", 1000, "Мотор"),
        Ad(4, "", 3400, "Облако"),
        Ad(5, "", 1100, "Укроп"),
        Ad(6, "", 190, "Тарелка"),
    )

    val fav = listOf(
        Ad(1, "", 100, "Кружка"),
        Ad(4, "", 3400, "Облако"),
        Ad(6, "", 190, "Тарелка"),
    )

    override fun getRecommendedAds(): Single<List<Ad>> = Single.just(ads)

    override fun getFavoritesAds(): Single<List<Ad>> = Single.just(fav)
}